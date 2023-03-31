<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <h4>You are receiving this confirmation email.

    <table>
        <thead>
            <tr>
                <th>Item</th>
                <th>Recent CN</th>
                <th>Recent Vendor</th>
                <th>Order Quantity</th>
                <th>Comments</th>
            </tr>
        </thead>
        <tbody>
        <#list requestItems as request>
            <tr>
                <td>${request.masterItem().item()!""}</td>
                <td>${request.masterItem().recent_cn()!''}</td>
                <td>${request.masterItem().recent_vendor()!""}</td>
                <td>${request.order_quantity()!""}</td>
                <td>${request.masterItem().comments()!""}</td>
                <td>${request.user()!""}</td>
                td>${request.department()!""}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</body>
</html>