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
          (swap-faces :x :y {:colors
                       {:x :blue :y :red}}))))
  
  (testing "rotate-xy"
    (is (=
          {:position
           [-1 1 1]}
          (rotate-in-xy-plane {:position [1 1 1]}))))

  (testing "rotate-yz"
    (is (=
          {:position
           [1 -1 0]}
          (rotate-in-yz-plane {:position [1 0 1]})))
    (is (=
          {:position
           [1 -1 -1]}
          (rotate-in-yz-plane {:position [1 -1 1]})))
    (is (=
          {:position
           [1 -1 1]}
          (rotate-in-yz-plane {:position [1 1 1]}))))
  )
