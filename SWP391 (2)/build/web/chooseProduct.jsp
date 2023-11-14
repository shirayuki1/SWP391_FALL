<%-- 
    Document   : chooseProduct
    Created on : Sep 28, 2023, 5:30:45 PM
    Author     : Admin
--%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Chọn sản phẩm so sánh</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script type="text/javascript" src="scripts/jquery.formatCurrency.js"></script>
        
        <link href="css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.1/css/jquery.dataTables.min.css">

    </head>

   <body onload="time()" class="app sidebar-mini rtl">
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách sản phẩm</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
    <div class="col-md-12">
        <div class="tile">
            <div class="tile-body">
                <div class="row element-button">
                    <a href="ProductDetailServlet?productID=${requestScope.id1}" class="btn btn-secondary mb-4" style="color: black;">
                        <i class="fas fa-arrow-left" style="color: black;"></i> <span style="color: black;">Quay về</span>
</a>
                    <c:forEach items="${list}" var="i">
                        <div class="col-lg-12 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <img src="img/${i.image}" alt="Cho nay la anh" width="100px;">
                                        </div>
                                        <div class="col-md-8">
                                            <h4 class="card-title">${i.name}</h4>
                                            <p class="card-text"><span class="font-weight-bold text-black">Số lượng : ${i.stock}</p>
                                            <p class="card-text"><span class="font-weight-bold text-black">Đơn giá : ${i.price} VND></p>
                                            <p class="card-text"><span class="font-weight-bold text-black">Kích Thước : ${i.size}</p>
                                            <button class="btn btn-primary btn-lg trash" type="button" 
                                                    onclick="window.location.href = 'ProductDetailServlet?productID=${requestScope.id1}&id2=${i.productID}';"
                                                    >
                                                <i class="fas fa-balance-scale"></i> So sánh 
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

        </main>

        <!--
          MODAL
        -->
        <div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
             data-keyboard="false">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group  col-md-12">
                                <span class="thong-tin-thanh-toan">
                                    <h5>Chỉnh sửa thông tin sản phẩm cơ bản</h5>
                                </span>
                            </div>
                        </div>
                        <form action="update" method="POST" id="updatesp">


                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>
        <!--
        MODAL
        -->

        <!-- Essential javascripts for application to work-->
        <script src="./js/jquery-3.2.1.min.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="./js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="./js/plugins/dataTables.bootstrap.min.js"></script>

        <script>
                                                            function deleteRow(r) {
                                                                var i = r.parentNode.parentNode.rowIndex;
                                                                document.getElementById("myTable").deleteRow(i);
                                                            }
                                                            function isDelete(i) {
                                                                var check = confirm('Do you want to delete product with id = ' + i);
                                                                if (check) {
                                                                    window.window.location.href = '${pageContext.request.contextPath}/deleteProduct?id=' + i;
                                                                }
                                                            }
                                                            oTable = $('#sampleTable').dataTable();
                                                            $('#all').click(function (e) {
                                                                $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
                                                                e.stopImmediatePropagation();
                                                            });
                                                            function getData(i, id1) {
                                                                $.ajax({
                                                                    type: 'GET',
                                                                    url: '${pageContext.request.contextPath}/loadProductDetail',
                                                                    data: {
                                                                        id2: i,
                                                                        id: id1
                                                                    },

                                                                })
                                                            }

                                                            var format = function (num) {
                                                                var str = num.toString().replace("$", ""), parts = false, output = [], i = 1, formatted = null;
                                                                if (str.indexOf(".") > 0) {
                                                                    parts = str.split(".");
                                                                    str = parts[0];
                                                                }
                                                                str = str.split("").reverse();
                                                                for (var j = 0, len = str.length; j < len; j++) {
                                                                    if (str[j] != ",") {
                                                                        output.push(str[j]);
                                                                        if (i % 3 == 0 && j < (len - 1)) {
                                                                            output.push(",");
                                                                        }
                                                                        i++;
                                                                    }
                                                                }
                                                                formatted = output.reverse().join("");
                                                                return("$" + formatted + ((parts) ? "." + parts[1].substr(0, 2) : ""));
                                                            };

                                                            function searchProduct() {
                                                                var id = $('#productID').val();
                                                                var name = $('#productName').val();
                                                                var quantity = $('#productQuantity').val();
                                                                var status = $('#status').val();
                                                                var price = $('#productPrice').val();
                                                                var categoryID = $('#category').val();
                                                                $.ajax({
                                                                    type: 'POST',
                                                                    url: '${pageContext.request.contextPath}/product',
                                                                    data: {
                                                                        id: id,
                                                                        name: name,
                                                                        quantity: quantity,
                                                                        status: status,
                                                                        price: price,
                                                                        categoryID: categoryID
                                                                    },
                                                                    dataType: "json",
                                                                    success: function (data, textStatus, jqXHR) {
                                                                        table.clear();
                                                                        for (let idx = 0; idx < data.length; idx++) {
                                                                            table.row.add({
                                                                                0: '<input type="checkbox" name="check1" >',
                                                                                1: data[idx].productID,
                                                                                2: data[idx].productName,
                                                                                3: '<img src="img/' + data[idx].productLink + '" alt="" width="100px;">',

                                                                                4: data[idx].quantity,
                                                                                5: '<span class=\"badge bg-success\">' + (data[idx].quantity > 0 ? 'Còn hàng' : 'Hết hàng') + '</span>',
                                                                                6: format(data[idx].productPrice),
                                                                                7: data[idx].category.name,
                                                                                8: '<button class="btn btn-primary btn-sm trash" type="button" onclick="isDelete(' + data[idx].productID + ')" title="Xóa"><i class="fas fa-trash-alt"></i> ' +
                                                                                        '</button>' +
                                                                                        '<button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp" data-toggle="modal" data-target="#ModalUP" onclick="getData(' + data[idx].productID + ')"><i class="fas fa-edit"></i></button>'
                                                                            })
                                                                        }
                                                                        table.draw(false)
                                                                    }
                                                                })
                                                            }
        </script>
    </body>

</html>
