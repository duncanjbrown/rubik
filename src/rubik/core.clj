(ns rubik.core
  (:require [rubik.transform :refer :all]
            [rubik.cube :refer :all]
            [rubik.query :refer :all])
  (:gen-class))

(defn main [] (algo default-cube '(:U :D :L :R)))

