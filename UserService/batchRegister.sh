#!/bin/bash

# URL endpoint API
URL="http://localhost:8081/auth/v1/register/customer"

# Data dasar untuk username dan email
BASE_USERNAME="customer"
BASE_EMAIL="customer@com"

# Jumlah iterasi yang diinginkan (misalnya 5 kali)
ITERATIONS=100

# Loop untuk mengirimkan request curl
for ((i=3; i<=ITERATIONS; i++))
do
    # Menambahkan nomor iterasi pada username dan email
    USERNAME="${BASE_USERNAME}00${i}"
    EMAIL="${BASE_EMAIL}.${i}"

    echo "Mengirim request ke-$i dengan username: $USERNAME dan email: $EMAIL..."

    # Data yang akan dikirimkan
    DATA="{
        \"username\": \"$USERNAME\",
        \"password\": \"12345678\",
        \"confirmPassword\": \"12345678\",
        \"email\": \"$EMAIL\"
    }"

    # Melakukan request curl
    curl --location "$URL" \
    --header 'Content-Type: application/json' \
    --data-raw "$DATA"
    echo -e "\nRequest ke-$i selesai.\n"
done

echo "Selesai mengirimkan $ITERATIONS request."
