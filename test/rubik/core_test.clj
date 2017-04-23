(ns rubik.core-test
  (:require [clojure.test :refer :all]
            [rubik.core :refer :all]))

(deftest a-test
  (testing "rotate-90"
    (is (= [-1 1] (rotate-90 [1 1])))
    (is (= [-1 -1] (rotate-90 [-1 1])))
    (is (= [1 -1] (rotate-90 [-1 -1])))
    (is (= [1 1] (rotate-90 [1 -1]))))

  (testing "swap-faces"
    (is (=
          {:colors
           {:x :red :y :blue}}
          (swap-faces {:colors
                       {:x :blue :y :red}} :x :y)))))
