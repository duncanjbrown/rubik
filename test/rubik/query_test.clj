(ns rubik.query-test
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
           (map (comp :y :colors) (get-face :N default-cube))))
    (is (= (repeat 9 :green) 
           (map (comp :y :colors) (get-face :S default-cube))))
    (is (= (repeat 9 :red) 
           (map (comp :x :colors) (get-face :E default-cube))))
    (is (= (repeat 9 :orange) 
           (map (comp :x :colors) (get-face :W default-cube)))))

  (testing "sort-pieces-by-axis"
    (is (= [1 1 1 0 0 0 -1 -1 -1]
           (map (comp :z :position) 
                (sort-pieces-by-axis :z > (get-face :N default-cube))))))

  (testing "get-aspect"
    (is (= :z
           (get-aspect (get-face :U default-cube))))
    (is (= :y
           (get-aspect (get-face :N default-cube))))
    (is (= :x
           (get-aspect (get-face :E default-cube))))
    (is (= nil
           (get-aspect (flatten [(get-face :E default-cube) (get-face :U default-cube)])))))

  (testing "returns the E face sorted with Z=1 and Y=-1 at the top left"
    (is (= {:x 1 :y -1 :z 1}
           (:position (first (get-sorted-face :E default-cube))))))

  (testing "returns the W face sorted with Z=1 and Y=1 at the top left"
    (is (= {:x -1 :y 1 :z 1}  
           (:position (first (get-sorted-face :W default-cube))))))

  (testing "returns the U face sorted with Y=1 and X=-1 at the top left"
    (is (= {:x -1 :y 1 :z 1} 
          (:position (first (get-sorted-face :U default-cube))))))

  (testing "returns the D face sorted with Y=-1 and X=-1 at the top left"
    (is (= {:x -1 :y -1 :z -1} 
          (:position (first (get-sorted-face :D default-cube))))))

  (testing "returns the N face sorted with Z=1 and X=1 at the top left"
    (is (= {:x 1 :y 1 :z 1} 
          (:position (first (get-sorted-face :N default-cube))))))

  (testing "returns the S face sorted with Z=1 and X=-1 at the top left"
    (is (= {:x -1 :y -1 :z 1} 
          (:position (first (get-sorted-face :S default-cube)))))))


  ;; (testing "sort-pieces-on-side"
  ;;   (is (= [[-1 1] [0 1] [1 1]
  ;;           [0 -1] [0 0] [1 0]
  ;;           [-1 -1] [0 -1] [1 -1]]
  ;;           (sort-pieces-on-side (get-face :U default-cube)))) 

;;   (testing "get-face"
;;     (is (= (repeat 3 '(:blue :blue :blue)) (get-face default-cube :N))))
;;     (is (= (repeat 3 '(:green :green :green)) (get-face default-cube :S))))
;;     (is (= (repeat 3 '(:orange :orange :orange)) (get-face default-cube :E)))
;;     (is (= (repeat 3 '(:red :red :red)) (get-face default-cube :W)))
;;     (is (= (repeat 3 '(:white :white :white)) (get-face default-cube :U)))
;;     (is (= (repeat 3 '(:yellow :yellow :yellow)) (get-face default-cube :D)))
;;
