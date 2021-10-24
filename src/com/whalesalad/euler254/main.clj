(ns com.whalesalad.euler254.main
  "https://projecteuler.net/problem=254"
  (:require [clojure.string :as str]))

(def sum
  (partial reduce +))

(defn num-to-digits [n]
  (->> n
      str
      seq
      (map #(Character/getNumericValue %))))


(def sum-digits
  (comp sum num-to-digits))


(def fac 
  (fn [n]
    (loop [x n
           result 1]
      (if (zero? x)
        result
        (recur (dec x) (* x result))))))


;; euler stuff

;; f(n)
(defn sum-fac [n]
  (->> n
       num-to-digits
       (map fac)
       sum))


;; sf(n)
(defn sum-fac-digits [n]
  (->> n
       sum-fac
       sum-digits))


;; g(i)
(defn find-smallest-n [i]
  "Find the smallest int, x, 
   where (= i (sum-fac-digits x))"
  (loop [x 1]
    (if (= i (sum-fac-digits x))
      x
      (recur (inc x)))))


;; sg(i)
(defn sum-find-smallest-n [i]
  (-> i
      find-smallest-n
      sum-digits))


(defn smallest-up-to-n
  ([b] 
   (do-the-thing 1 b))
  ([a b]
   (->> (range a (inc b))
        (map sum-find-smallest-n)
        (reduce +))))