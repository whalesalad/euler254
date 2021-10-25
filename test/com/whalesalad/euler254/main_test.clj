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

(defn get-test-data-by-kind-and-id [kind test-id]
  (let [kind (name kind)
        path (->> [kind "/" kind test-id ".txt"]
                  (reduce str)
                  (prefix-fixture-path))
        text (slurp path)]
    (-> text
        str/split-lines)))

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

(deftest test-opening-test-data
  (let [expected {:input ["2" "3 1000000" "20 1000000"]
                  :output ["8" "156"]}]
    (is (= expected 
           (first (get-test-data))))))

(deftest test-case-00
  (let [data (first (get-test-data))
        input (->> (get data :input)
                   (drop 1)
                   (map (fn [x]
                          (-> x
                              (str/split #" ")
                              first
                              read-string))))
        output (->> (get data :output)
                    (map read-string))
        pairs (map vector input output)]
    (doseq [[a b] pairs]
      (is (= (smallest-up-to-n a) 
             b)))))