(ns com.whalesalad.euler254.main-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [com.whalesalad.euler254.main :refer :all]))

(defn path-join [parts]
  (str/join "/" parts))

(defn prefix-fixture-path [p]
  (let [root (System/getProperty "user.dir")
        fixtures (path-join [root "test" "fixtures"])]
    (path-join [fixtures p])))


(defn map-values [value-fn input-map]
  (let [apply-to-values (fn [m k v]
                          (assoc m k (value-fn v)))]
    (reduce-kv apply-to-values
               {}
               input-map)))

(defn get-test-data-by-kind-and-id [kind test-id]
  (let [kind (name kind)
        path (->> [kind "/" kind test-id ".txt"]
                  (reduce str)
                  (prefix-fixture-path))
        text (slurp path)]
    (str/split-lines text)))

(defn get-test-data-by-id [test-id]
  (->> [:input :output]
       (mapcat
        (fn [kind]
          {kind (get-test-data-by-kind-and-id kind test-id)}))
       (into {})))

(defn get-test-data []
  (map get-test-data-by-id ["00"]))

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

;; g(i)
(deftest test-find-smallest-n
  (is (= 25
         (find-smallest-n 5)))
  (is (= 267
         (find-smallest-n 20))))

;; (deftest test-sum-find-smallest-n
;;   (is (= 156
;;          (sum-find-smallest-n 20))))

(deftest test-opening-test-data
  (is ))