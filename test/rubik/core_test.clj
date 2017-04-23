(ns rubik.core-test
  (:require [clojure.test :refer :all]
            [rubik.core :refer :all]))

(deftest a-test
  (testing "rotate-90"
    (is (= [-1 1] (rotate-90 [1 1])))
    (is (= [-1 -1] (rotate-90 [-1 1])))
    (is (= [1 -1] (rotate-90 [-1 -1])))
    (is (= [1 1] (rotate-90 [1 -1])))))
