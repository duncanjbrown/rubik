(ns rubik.query
  (:require [rubik.cube :refer :all])
  (:gen-class))

(defn get-face
  [face cube]
  (filter (:filter (face-mapping face)) cube))

(defn sort-pieces-by-axis
  [axis operator pieces]
  (sort-by #(axis (:position %)) operator pieces))

(defn get-aspect
  "Infer an aspect from a set of pieces, if possible. If not, return nil."
  [pieces]
  (first (drop-while 
           #(apply not= (map (comp % :position) pieces)) [:x :y :z])))

(defn get-face-colors
  "Get the relevant colours from the pieces comprising a face"
  [pieces]
  (let [aspect (get-aspect pieces)]
    (map (comp aspect :colors) pieces)))

(defn get-sorted-face
  [face cube]
  (let [aspect (:aspect (face-mapping face))
        pieces (get-face face cube)]
  (case aspect
        :x (flatten (map (partial sort-pieces-by-axis :y >) 
                (partition 3 (sort-pieces-by-axis :z > pieces))))
        :x' (flatten (map (partial sort-pieces-by-axis :y <) 
                (partition 3 (sort-pieces-by-axis :z > pieces))))
        :y (flatten (map (partial sort-pieces-by-axis :x >) 
                (partition 3 (sort-pieces-by-axis :z > pieces))))
        :y' (flatten (map (partial sort-pieces-by-axis :x <) 
                (partition 3 (sort-pieces-by-axis :z > pieces))))
        :z (flatten (map (partial sort-pieces-by-axis :x <) 
                (partition 3 (sort-pieces-by-axis :y > pieces))))
        :z' (flatten (map (partial sort-pieces-by-axis :x <) 
                (partition 3 (sort-pieces-by-axis :y < pieces))))
        )))
