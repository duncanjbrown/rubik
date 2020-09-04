(ns rubik.transform
  (:require [clojure.core.matrix :as m])
  (:require [rubik.cube :refer :all]
            [rubik.query :refer :all])
  (:gen-class))

(defn rotate'
  "Rotates the vector 90 degrees counterclockwise."
  [vector]
  (vec (map int (m/mmul [[0 -1] [1 0]] vector))))

(defn rotate
  "Rotates the vector 90 degrees clockwise."
  [vector]
  (vec (map int (m/mmul [[0 1] [-1 0]] vector))))

(defn swap-faces
  "Exchanges the two faces' colors in the piece."
  [face1 face2 piece]
  (-> piece
      (assoc-in [:colors face1] (get-in piece [:colors face2]))
      (assoc-in [:colors face2] (get-in piece [:colors face1]))))

(defn rotate-around-axis
  "Returns a function on a piece that rotates the piece around the specified axis, either clockwise or
  counterclockwise."
  [axis-of-rotation clockwise?]
  (let [axes (remove #{axis-of-rotation} [:x :y :z])]
    (fn [piece]
      (update piece :position
              (fn [coords]
                (into coords
                      (zipmap axes
                              (if (= true clockwise?)
                                (rotate (map coords axes))
                                (rotate' (map coords axes))))))))))

(defn rotate-piece-around-axis
  "Rotates a piece around the specified axis, either clockwise or counterclockwise."
  [axis clockwise? piece]
  (let [other-axes (remove #{axis} [:x :y :z])]
    (-> piece
      ((rotate-around-axis axis clockwise?))
      ((apply partial swap-faces other-axes)))))

(defn rotate-face
  "face => :U, :U', :D, :D', :F, :F', :B, :B', :L, :L', :R, :R'
  Rotates the input cube by the face specified."
  [cube face]
  (let [mapped-face (face-mapping face)]
    (let [dimension (:dimension mapped-face)]
      (let [clockwise? (:clockwise? mapped-face)]
        (let [to-rotate (get-face face cube)]
          (let [rotated-face (mapv (fn [i]
                                     (rotate-piece-around-axis dimension clockwise? i))
                                   to-rotate)]
            (map (fn [i]
                   (let [match (for [x rotated-face :when (= (:position x) (:position i))] x)]
                     (if (empty? match)
                       i
                       (first match)))
                   )
                 cube)
            )
          )
        ))))

(defn algo
  "cube => scrambled or default-cube
  steps => A list of steps. '(:R :U :R' :U')
  Executes the specified algorithm on the provided cube."
  [cube steps]
  (loop [current-step (first steps)
         steps-remaining (rest steps)
         input-cube cube]
    (if (not= current-step nil)
      (recur (first steps-remaining) (rest steps-remaining) (rotate-face input-cube current-step))
      input-cube)))