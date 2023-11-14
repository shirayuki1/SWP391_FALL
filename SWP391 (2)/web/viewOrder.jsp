<%-- 
    Document   : viewRequest
    Created on : Oct 19, 2023, 12:14:52 PM
    Author     : Admin
--%>

<%@page import="Model.Account"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Quản lý đơn đặt hàng</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
<!--        <link rel="stylesheet" type="text/css" href="./css/admin.css">-->
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

        <jsp:include page="headerUser.jsp"/>
        <%

            String[] status = {"Đang chờ xét duyệt", "Đã xét duyệt", "Đơn đặt hàng sẽ được thực hiện", "Đã từ chối"};
            request.setAttribute("statusArray", status);
        %>
        <main class="app-content">
            <div class="app-title" style="display: block;
                 display: -ms-flexbox;
                 display: flex;
                 -webkit-box-align: center;
                 -ms-flex-align: center;
                 align-items: center;
                 -webkit-box-pack: justify;
                 -ms-flex-pack: justify;
                 justify-content: space-between;
                 -webkit-box-orient: horizontal;
                 -webkit-box-direction: normal;
                 -ms-flex-direction: row;
                 flex-direction: row;
                 background-color: #FFF;
                 border-radius: .375rem;
                 padding: 10px 30px;
                 -webkit-box-shadow: 0 1px 2px rgb(0 0 0 / 10%);
                 box-shadow: 0 1px 2px rgb(0 0 0 / 10%);
                 border-left: 6px solid #FFD43B;
                 margin-bottom: 20px;">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách đơn đặt hàng</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile" style="    position: relative;
                         background: #ffffff;
                         border-radius: .375rem;
                         padding: 20px;
                         -webkit-box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
                         box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
                         margin-bottom: 30px;
                         -webkit-transition: all 0.3s ease-in-out;
                         -o-transition: all 0.3s ease-in-out;
                         transition: all 0.3s ease-in-out;">
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">
                                    <!--
                                                                        <a class="btn btn-add btn-sm" href="createProduct" title="Thêm"><i class="fas fa-plus"></i>
                                                                            Tạo mới sản phẩm</a>-->
                                    <!--                                </div>
                                                                    <div class="col-sm-2">
                                                                        <a class="btn btn-delete btn-sm nhap-tu-file" type="button" title="Nhập" onclick="myFunction(this)"><i
                                                                                class="fas fa-file-upload"></i> Tải từ file</a>
                                                                    </div>
                                    
                                                                    <div class="col-sm-2">
                                                                        <a class="btn btn-delete btn-sm print-file" type="button" title="In" onclick="myApp.printTable()"><i
                                                                                class="fas fa-print"></i> In dữ liệu</a>
                                                                    </div>
                                                                    <div class="col-sm-2">
                                                                        <a class="btn btn-delete btn-sm print-file js-textareacopybtn" type="button" title="Sao chép"><i
                                                                                class="fas fa-copy"></i> Sao chép</a>
                                                                    </div>
                                    
                                                                    <div class="col-sm-2">
                                                                        <a class="btn btn-excel btn-sm" href="" title="In"><i class="fas fa-file-excel"></i> Xuất Excel</a>
                                                                    </div>
                                                                    <div class="col-sm-2">
                                                                        <a class="btn btn-delete btn-sm pdf-file" type="button" title="In" onclick="myFunction(this)"><i
                                                                                class="fas fa-file-pdf"></i> Xuất PDF</a>
                                                                    </div>
                                                                    <div class="col-sm-2">
                                                                        <a class="btn btn-delete btn-sm" type="button" title="Xóa" onclick="myFunction(this)"><i
                                                                                class="fas fa-trash-alt"></i> Xóa tất cả </a>
                                                                    </div>-->
                                </div>
                                <table class="table table-hover table-bordered" id="sampleTable">
                                    <thead>
                                        <tr>
                                            <th width="10"><input type="checkbox" id="all"></th>
                                            <th>Tên sản phẩm</th>
                                            <th>Số lương </th>
                                            <th>Thời gian</th>
                                            <th>Tình trạng</th>
                                            <th>Giá</th>


                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="counter" value="0" scope="page" />
                                        <c:forEach items="${listAcc}" var="i">

                                            <tr>

                                                <td width="10"><input type="checkbox" name="check1" value="1"></td>
                                                <td>${i.name}</td>
                                                <td>${i.quantity}</td>
                                                <td>${i.orderDate}</td>
                                                <td>${i.status}</td>
                                                <td>${i.price}</td>



                                            </tr>

                                        </c:forEach>
                                    </tbody>
                                    <tr>
                                    </tr>
                                </table>
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
                                    <h5>Quản lý đơn đặt hàng</h5>
                                </span>
                            </div>
                        </div>
                        <form action="UpdateRequest" method="POST" id="updatesp">


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
        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-secondary mt-5 pt-5">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                    <h5 class="text-secondary text-uppercase mb-4">Thông tin liên lạc</h5>
                    <p class="mb-4">Chuyên cung cấp các loại chim</p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Tp.Hồ Chí Minh</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>binhdxse160***@fpt.edu.vn</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+84999999999</p>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="row">
                        <div class="col-md-4 mb-5">
                            <h5 class="text-secondary text-uppercase mb-4">Lồng Shop</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-secondary mb-2" href="homePage.jsp"><i class="fa fa-angle-right mr-2"></i>Trang chủ</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Kênh bán hàng</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Của hàng</a>                               
                                <a class="text-secondary" href="#"><i class="fa fa-angle-right mr-2"></i>Liên lạc với chúng tôi</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h5 class="text-secondary text-uppercase mb-4">Tài khoản của tôi</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Hồ sơ</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Giỏ hàng</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Đơn hàng</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h6 class="text-secondary text-uppercase mt-4 mb-3">Theo dõi chúng tôi</h6>
                            <div class="d-flex">
                                <a class="btn btn-primary btn-square mr-2" href="#"><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-primary btn-square mr-2" href="#"><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-primary btn-square mr-2" href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a class="btn btn-primary btn-square" href="#"><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->


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
        <script type="text/javascript">$('#sampleTable').DataTable();</script>

        <script>
            function deleteRow(r) {
                var i = r.parentNode.parentNode.rowIndex;
                document.getElementById("myTable").deleteRow(i);
            }
            function isDelete(i, customerID) {
                var check = confirm('We will delete your request in the DB' + i);
                if (check) {
                    window.window.location.href = '${pageContext.request.contextPath}/DeleteRequest?id=' + i + '&customerID=' + customerID;
                }
            }
            oTable = $('#sampleTable').dataTable();
            $('#all').click(function (e) {
                $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
                e.stopImmediatePropagation();
            });
            function getData(i) {
                $.ajax({
                    type: 'GET',
                    url: '${pageContext.request.contextPath}/LoadRequest',
                    data: {
                        id: i
                    },
                    success: function (data, textStatus, jqXHR) {
                        $('#updatesp').html(data);
                    }
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
