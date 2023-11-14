<%-- 
    Document   : managermanageRequest
    Created on : Oct 26, 2023, 1:17:42 PM
    Author     : Admin
--%>

<%@page import="Model.Account"%>
<%@page import="Dao.AccountDAO"%>
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
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <!-- Font-icon css-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" 
              integrity="sha384-GLhlTQ8iGQsWxK+5O2rDdduj0H+8EVoZO5IowBqkmz2KfaSGmCCtkEY5S02dM6YD" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script type="text/javascript" src="scripts/jquery.formatCurrency.js"></script>

    </head>

    <body onload="time()" class="app sidebar-mini rtl">
        
        <jsp:include page="managerpage.jsp"/>
        <%
            Account user = ((Account) session.getAttribute("userlogin"));
            int staffID = 0;
            String staffName = "";
                if(user.getRoleid().getName().equals("manager")){
                    staffID = user.getId();
                    staffName = user.getFirstname();
                }else request.getRequestDispatcher("Unauthorized.jsp").forward(request, response);
            
            
            String[] status = {"Đang chờ xét duyệt", "Đã xét duyệt", "Khách hàng đã xác nhận đơn hàng", "Đã từ chối"};
            request.setAttribute("statusArray", status);
        %>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách đơn đặt hàng</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
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
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Nguyên liệu</th>
                                        <th>Người tạo</th>
                                        <th>Tình trạng</th>
                                        <th>Chức năng</th>
                                        
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="counter" value="0" scope="page" />
                                    <c:forEach items="${list}" var="i">
                                        <c:set var="currentAcc" value="${listAcc[counter]}" />
                                        <tr>
                                            
                                            <td width="10"><input type="checkbox" name="check1" value="1"></td>
                                            <td>${i.id}</td>
                                            <td>${i.name}</td>
                                            <td>
                                                ${i.material.original}
                                            </td>
                                            <td>
                                                 <!-- Check the entire object -->
                                                ${currentAcc.getFirstname()}
                                            </td>
                                            <td><c:out value="${statusArray[i.status]}" /></td>
                                            <td>
                                                <c:if test="${i.status eq 0}">
                                                    <form style="width: 35px" action="UpdateRequest" method="post">
                                                        <input type="hidden" name="ID" value="${i.id}">
                                                        <input type="hidden" name="staffID" value="<%= staffID %>">
                                                        <input type="hidden" name="staffName" value="<%= staffName %>">
                                                        <input type="hidden" name="name" value="${i.name}">
                                                        <input type="hidden" name="quantity" value="${i.quantity}">
                                                        <input type="hidden" name="price" value="${i.price}">
                                                        <input type="hidden" name="materialID" value="${i.material.id}">
                                                        <input type="hidden" name="materialName" value="${i.material.original}">
                                                        <input type="hidden" name="size" value="${i.size}">
                                                        <input type="hidden" name="status" value="${i.status}">
                                                        <input type="hidden" name="accountID" value="${currentAcc.getId()}">
                                                        <input type="hidden" name="accountName" value="${currentAcc.getFirstname()}">
                                                        <button style="height: 29.5px;" class="btn btn-primary btn-sm edit" type="submit" title="Sửa">
                                                            <i class="fas fa-edit">Approve</i>
                                                        </button>
                                                    </form>
                                                        <button class="btn btn-primary btn-sm trash" type="button" onclick="isDelete('${i.id}')" title="Xóa"
                                                                <i class="fa-solid fa-x">Deny</i>
                                                </button>
                                                </c:if>
<!--                                                <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp" data-toggle="modal"
                                                        data-target="#ModalUP" onclick="getData('${i.id}')"><i class="fas fa-edit"></i>
                                                </button>-->
                                                
                                            </td>
                                        </tr>
                                        <c:set var="counter" value="${counter + 1}" scope="page" />
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
            function isDelete(i) {
                var check = confirm('Từ chối đơn đặt hàng ' + i + '?');
                if (check) {
                    window.window.location.href = '${pageContext.request.contextPath}/DeleteRequest?id=' + i;
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
