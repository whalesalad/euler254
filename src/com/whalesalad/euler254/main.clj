(ns com.whalesalad.euler254.main)
;; https://projecteuler.net/problem=254


(def sum (partial reduce +))


(defn num-to-digits [n]
  (->> n
      str
      seq
      (map #(Character/getNumericValue %))))


(def sum-digits
  (comp sum num-to-digits))


(defn dbl [x]
  (* 2 x))


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
  "Find the smallest int, x, where (= i (sum-fac-digits x))"
  (loop [x 2]
    (if (= i (sum-fac-digits x))
      x
      (recur (inc x)))))

(defn sum-find-smallest-n [i]
  (-> i
      find-smallest-n
      sum-digits))