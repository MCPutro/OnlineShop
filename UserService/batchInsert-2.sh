#!/bin/bash

# URL API
API_URL="http://localhost:8081/public/v1/customer"

# Mengirim 100 data POST ke API secara konkuren
for i in {1..500}
do
    USERNAME="user.testing-.$i"
    EMAIL="user-$i@example.com"
    ROLE="USER-password"  # Anda bisa mengubah ini menjadi "ADMIN" jika diperlukan

    # Mengirim data POST ke API di latar belakang
    {
        response=$(curl -s -o /dev/null -w "%{http_code}" -X POST $API_URL \
        -H "Content-Type: application/json" \
        -d '{
            "username": "'"$USERNAME"'",
            "email": "'"$EMAIL"'",
            "password": "'"$ROLE"'"
        }')

        echo "Sent data for $USERNAME: HTTP Status $response"
    } &  # Menjalankan proses di latar belakang
done

# Menunggu semua proses selesai
wait

echo "All requests sent to $API_URL"