(ns rubik.transform
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

