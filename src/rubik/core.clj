(ns rubik.core
  (:require [clojure.core.matrix :as m])
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
  (let [mapping (face-mapping face)
        aspect (:aspect mapping)]
    (filter (:filter mapping cube))))

(defn move
  [axis offset cube]
  (let [to-rotate (filter #(= offset (axis (:position %))) cube)]
    (println to-rotate)
    (into
      (remove (set to-rotate) cube)
      (map (partial rotate-piece-around-axis axis) to-rotate))))

(def default-cube [
                   ;; Top of cube
                   {:position {:x 0 :y 0 :z 1}
                    :colors {
                             :x nil
                             :y nil
                             :z :white
                             }}
                   {:position {:x -1 :y -1 :z 1}
                    :colors {
                             :y :green
                             :x :orange
                             :z :white
                             }}
                  {:position {:x -1 :y 0 :z 1}
                   :colors {
                            :y nil
                            :x :orange
                            :z :white
                            }}
                  {:position {:x -1 :y 1 :z 1}
                   :colors {
                            :y :blue
                            :x :orange
                            :z :white
                            }}
                  {:position {:x 0 :y 1 :z 1}
                   :colors {
                            :y :blue
                            :x nil
                            :z :white
                            }}
                  {:position {:x 1 :y 1 :z 1}
                   :colors {
                            :y :blue
                            :x :red
                            :z :white
                            }}
                  {:position {:x 1 :y 0 :z 1}
                   :colors {
                            :y nil
                            :x :red
                            :z :white
                            }}
                  {:position {:x 1 :y -1 :z 1}
                   :colors {
                            :y :green
                            :x :red
                            :z :white
                            }}
                  {:position {:x 0 :y -1 :z 1}
                   :colors {
                            :y :green
                            :x nil
                            :z :white
                            }}


                  ;; Middle of cube
                  {:position {:x -1 :y -1 :z 0}
                   :colors {
                            :y :green
                            :x :orange
                            :z nil
                            }}
                  {:position {:x -1 :y 0 :z 0}
                   :colors {
                            :y nil
                            :x :orange
                            :z nil
                            }}
                  {:position {:x -1 :y 1 :z 0}
                   :colors {
                            :y :blue
                            :x :orange
                            :z nil
                            }}
                  {:position {:x 0 :y 1 :z 0}
                   :colors {
                            :y :blue
                            :x nil
                            :z nil
                            }}
                  {:position {:x 1 :y 1 :z 0}
                   :colors {
                            :y :blue
                            :x :red
                            :z nil
                            }}
                  {:position {:x 1 :y 0 :z 0}
                   :colors {
                            :y nil
                            :x :red
                            :z nil
                            }}
                  {:position {:x 1 :y -1 :z 0}
                   :colors {
                            :y :green
                            :x :red
                            :z nil
                            }}
                  {:position {:x 0 :y -1 :z 0}
                    :colors {
                            :y :green
                            :x nil
                            :z nil
                            }}

                  ;; Bottom of cube

                  {:position {:x -1 :y -1 :z -1}
                   :colors {
                            :y :green
                            :x :orange
                            :z :yellow
                            }}
                 {:position {:x -1 :y 0 :z -1}
                  :colors {
                           :y nil
                           :x :orange
                           :z :yellow
                           }}
                 {:position {:x -1 :y 1 :z -1}
                  :colors {
                           :y :blue
                           :x :orange
                           :z :yellow
                           }}
                 {:position {:x 0 :y 1 :z -1}
                  :colors {
                           :y :blue
                           :x nil
                           :z :yellow
                           }}
                 {:position {:x 1 :y 1 :z -1}
                  :colors {
                           :y :blue
                           :x :red
                           :z :yellow
                           }}
                 {:position {:x 1 :y 0 :z -1}
                  :colors {
                           :y nil
                           :x :red
                           :z :yellow
                           }}
                 {:position {:x 1 :y -1 :z -1}
                  :colors {
                           :y :green
                           :x :red
                           :z :yellow
                           }}
                 {:position {:x 0 :y -1 :z -1}
                  :colors {
                           :y :green
                           :x nil
                           :z :yellow
                           }}
                  {:position {:x 0 :y 0 :z -1}
                  :colors {
                            :y nil
                            :x nil
                            :z :yellow
                            }}
                   ])

(defn face-mapping
  [face]
  (face {
         :N {
             :filter #(= 1 (:y (:position %)))
             :aspect :y
             }
         :S {
             :filter #(= -1 (:y (:position %)))
             :aspect :y
             }
         :W {
             :filter #(= 1 (:x (:position %)))
             :aspect :x
             }
         :E {
             :filter #(= -1 (:x (:position %)))
             :aspect :x
             }
         :U {
             :filter #(= 1 (:z (:position %)))
             :aspect :z
             }
         :D {
             :filter #(= -1 (:z (:position %)))
             :aspect :z
             }
         }))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
