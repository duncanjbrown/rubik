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
          (rotate-xy {:position [1 1 1]}))))

  (testing "rotate-x"
    (is (=
         [
          {:colors
           {:x :red :y :green :z :white}
           :position [1 -1 1]}
          {:colors
           {:x nil :y :green :z :white}
           :position [1 0 1]}
          {:colors
           {:x :orange :y :green :z :white}
           :position [1 1 1]}
          ]
         (rotate-x
          [
           {:colors
            {:x :green :y :red :z :white}
            :position [-1 -1 1]}
           {:colors
            {:x :green :y nil :z :white}
            :position [0 -1 1]}
           {:colors
            {:x :green :y :orange :z :white}
            :position [1 -1 1]}
           ]
           ))))
  )
