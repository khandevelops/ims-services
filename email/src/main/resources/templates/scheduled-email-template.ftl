<!DOCTYPE html>
<html lang="">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Item</th>
                <th>Recent CN</th>
                <th>Recent Vendor</th>
                <th>Total Quantity</th>
                <th>Order Quantity</th>
                <th>Min Quantity</th>
                <th>Max Quantity</th>
                <th>Comments</th>
            </tr>
        </thead>
        <tbody>
        <#list requestItems as request>
            <tr>
                <td>${request.item()!""}</td>
                <td>${request.recent_cn()!''}</td>
                <td>${request.recent_vendor()!""}</td>
                <td>${request.total_quantity()!""}</td>
                <td>${request.order_quantity()!""}</td>
                <td>${request.min_quantity()!""}</td>
                <td>${request.max_quantity()!""}</td>
                <td>${request.comments()!""}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</body>
</html>