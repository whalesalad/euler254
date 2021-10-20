(ns com.whalesalad.euler254.main-test
  (:require [clojure.test :refer :all]
            [com.whalesalad.euler254.main :refer :all]))

(deftest test-dbl
  (is (= 4 (dbl 2))))


(deftest test-fac
  (is (= 120 (fac 5))))


(deftest test-num-to-digits
  (is (= (list '3 '4 '2)
         (num-to-digits "342"))))


(deftest test-sum-fac
  (is (= 32
         (sum-fac 342))))

(deftest test-sum-fac-digits
  (is (= 5
         (sum-fac-digits 342)))
  (is (= 5
         (sum-fac-digits 25))))

;; (deftest test-find-smallest-n
;;   (is (= )))