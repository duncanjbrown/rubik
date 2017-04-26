(ns rubik.query
  (:require [rubik.cube :refer :all])
  (:gen-class))

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
  [face cube]
  (filter (:filter (face-mapping face)) cube))

(defn sort-pieces-by-axis
  [axis operator pieces]
  (sort-by #(axis (:position %)) operator pieces))

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
