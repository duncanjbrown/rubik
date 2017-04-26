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
  [axis pieces]
  (sort-by #(axis (:position %)) > pieces))

(defn get-aspect
  "Infer a face from a set of pieces, if possible. If not, return nil."
  [pieces]
  (first (drop-while (fn 
                       [axis]
                       (apply not= (map (comp axis :position) pieces))
                       ) [:x :y :z]))
  )
