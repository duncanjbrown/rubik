(ns rubik.query-tests
  (:require [clojure.test :refer :all]
            [rubik.cube :refer :all]
            [rubik.query :refer :all]))

(deftest query-test
  (testing "get-face on a face of the default cube returns pieces with the right color in the right plane"
    (is (= (repeat 9 :white)
           (map (comp :z :colors) (get-face :U default-cube))))
    (is (= (repeat 9 :yellow)
           (map (comp :z :colors) (get-face :D default-cube))))
    (is (= (repeat 9 :blue)
           (map (comp :y :colors) (get-face :B default-cube))))
    (is (= (repeat 9 :green)
           (map (comp :y :colors) (get-face :F default-cube))))
    (is (= (repeat 9 :red)
           (map (comp :x :colors) (get-face :R default-cube))))
    (is (= (repeat 9 :orange)
           (map (comp :x :colors) (get-face :L default-cube))))))