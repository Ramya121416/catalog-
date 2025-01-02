# catalog-
problem statement - simplified version of Shamir's Secret Sharing algorithm.


1. The polynomial has the form `f(x) = a_m * x^m + a_{m-1} * x^(m-1) + ... + a_1 * x + c`, where the degree of the polynomial is `m = k - 1` (with `k` being the number of roots needed to solve for the coefficients).
2. The input JSON provides `n` roots, each represented by a base and an encoded value for `y` (the value of the polynomial at `x`).
3. You need to decode the `y` values from different bases (binary, hexadecimal, etc.), then use these decoded values to calculate the secret constant term `c` using methods like Lagrange interpolation or matrix methods.
4. You should read the test case from a JSON file, decode the `y` values, and solve for `c`.
5. Finally, output the secret `c` for both test cases.

The roots and polynomial coefficients are to be extracted from the given data and then used in a method to reconstruct the polynomial and calculate the constant term.

{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2",
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
}


output is  3
