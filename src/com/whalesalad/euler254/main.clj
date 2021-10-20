(ns com.whalesalad.euler254.main)


(defn num-to-digits [n]
  (->> n
      str
      seq
      (map #(Character/getNumericValue %))))


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
       (reduce +)))


;; sf(n)
(defn sum-fac-digits [n]
  (->> n
       sum-fac
       num-to-digits
       (reduce +)))


;; g(i)
(defn find-smallest-n [i]
  "Find the smallest int, x, where (= i (sum-fac-digits x))")