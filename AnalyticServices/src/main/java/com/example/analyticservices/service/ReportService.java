package com.example.analyticservices.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final JdbcTemplate jdbcTemplate;

    public ReportService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Map<String, Object>> getOrderReport(LocalDate start, LocalDate end) {
        String sql = """
                    SELECT o.ID            as 'Order ID',
                           o.OrderStatus   as 'Order Status',
                           o.OrderDate     as 'Order Date',
                           o.TotalPrice    as 'Order Price',
                           p.Name          as 'Product Name',
                           p.SKU           as 'SKU Product',
                           oi.ProductPrice as 'Product Price',
                           oi.Quantity     as 'Quantity',
                           u.Name          as 'Customer Name',
                           u.Email         as 'Email User',
                           ua.Address      as 'Customer Address',
                           ua.Regency      as 'Regency',
                           ua.PostalCode   as 'Postal Code',
                           ua.Province     as 'Province'
                    FROM Transactions.Orders o
                    JOIN Transactions.OrderItems oi ON o.ID = oi.OrderId
                    JOIN Product.Products p ON p.ID = oi.ID
                    JOIN Person.Users u ON u.ID = o.UserId
                    JOIN Person.UserAddress ua ON u.ID = ua.UserId AND ua.ID = o.AddressId
                    WHERE o.OrderDate BETWEEN ? AND ?
                """;
        return jdbcTemplate.queryForList(sql, start, end); // Mengembalikan data sebagai List of Map
    }
}
