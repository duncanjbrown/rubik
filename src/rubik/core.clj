(ns rubik.core
  (:require [clojure.core.matrix :as m]
            [rubik.cube :refer :all])
  (:gen-class))

(defn rotate-90
  [vctr]
  (vec (map int (m/mmul [[0 -1] [1 0]] vctr))))

(defn swap-faces
  [face1 face2 piece]
  (-> piece
      (assoc-in [:colors face1] (get-in piece [:colors face2]))
      (assoc-in [:colors face2] (get-in piece [:colors face1]))))

(defn rotate-around-axis
  [axis-of-rotation]
  (let [axes (remove #{axis-of-rotation} [:x :y :z])]
    (fn [piece]
      (update piece :position
              (fn [coords]
                (into coords (zipmap axes (rotate-90 (map coords axes)))))))))

(defn rotate-piece-around-axis
  [axis piece]
  (let [other-axes (remove #{axis} [:x :y :z])]
    (-> piece
      ((rotate-around-axis axis))
      ((apply partial swap-faces other-axes)))))

;; (defn get-face
;;   [cube face]
;;   (let [mapping (face-mapping face)
;;         aspect (:aspect mapping)
;;         pieces (filter (:filter mapping) cube)]
;;  (partition 3 (map aspect (map :colors (case aspect
;;       :x (sort-by #(:y (:position %)) > (sort-by #(:z (:position %)) > pieces))
;;       :y (sort-by #(:x (:position %)) > (sort-by #(:z (:position %)) > pieces))
;;       :z (sort-by #(:x (:position %)) > (sort-by #(:y (:position %)) > pieces))
;;       ))))))

(defn get-face
  [cube face]
  (filter (:filter (face-mapping face) cube)))

(defn move
  [axis offset cube]
  (let [to-rotate (filter #(= offset (axis (:position %))) cube)]
    (println to-rotate)
    (into
      (remove (set to-rotate) cube)
      (map (partial rotate-piece-around-axis axis) to-rotate))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
