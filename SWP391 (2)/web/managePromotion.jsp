<%@page import="Model.Account"%>
<%-- 
    Document   : managePromotion
    Created on : Oct 8, 2023, 1:18:14 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý khuyến mãi</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="css/view.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <jsp:include page="managerpage.jsp"/>
        <!--Check login-->
        

        <!-- Navbar-->

        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                  <li><a class="app-nav__item" href="Logout"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>

        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item"><a href="OrderServlet">Danh sách khuyến mãi</a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">

                                    <a class="btn btn-add btn-sm" href="AddPromotion" title="Thêm"><i class="fas fa-plus"></i>
                                        Tạo mới khuyến mãi</a>
                                </div>
                                <div class="col-sm-2" style="display: flex; align-items: center;">
                                    <form style="display: flex;">
                                        <label style="margin-right: 5px;" for="startDate">Từ:</label>
                                        <input type="date" id="startDate" class="form-control" placeholder="Ngày bắt đầu">
                                        <label style="margin-right: 5px;" for="endDate">Đến:</label>
                                        <input type="date" id="endDate" class="form-control" placeholder="Ngày kết thúc">
                                        <button style="margin-left: 5px;" class="btn btn-primary btn-sm" type="button" onclick="filterByDate()" title="Tìm kiếm">
                                            <i class="fas fa-search"></i> Tìm kiếm
                                        </button>
                                    </form>
                                </div>


                            </div>

                            <table class="table table-hover table-bordered" id="sampleTable">
                                <thead>
                                    <tr>

                                        <th>ID khuyến mãi</th>
                                        <th>Tên khuyến mãi</th>
                                        <th>Ngày bắt đầu</th>
                                        <th>Ngày kết thúc</th>
                                        <th>Giá trị giảm</th>                                        
                                        <th>Người tạo</th>
                                        <th>Điều kiện</th>
                                        <th>Tính năng</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listPromotion}" var="v">
                                        <%--<c:if test="${v.getPromotionID() != 1}">--%>
                                            <tr> 

                                                <td>${v.getId()}</td> 
                                                <td>${v.getName()}</td>  
                                                <td>${v.getDateStart()}</td>   
                                                <td>${v.getDateEnd()}</td>   
                                                
                                                <td>${v.getValue()}%</td> 
                                                <td>${v.getAccount().getLastname()}</td> 
                                                <td>Từ ${v.getCondition()} sản phẩm</td> 
                                                <td style="display: flex">

                                                    <form style="width: 35px" id="deleteForm" action="DeletePromotion?promotionID=${v.getId()}" method="post">
                                                        <button style="height: 29.5px;" class="btn btn-primary btn-sm trash" type="submit" title="Xóa" onclick="confirmDelete();">
                                                            <i class="fas fa-trash-alt"></i>
                                                        </button>
                                                    </form>

                                                    <form style="width: 35px" action="UpdatePromotion" method="post">
                                                        <input type="hidden" name="promotionID" value="${v.getId()}">
                                                        <input type="hidden" name="promotionName" value="${v.getName()}">
                                                        <input type="hidden" name="dateStart" value="${v.getDateStart()}">
                                                        <input type="hidden" name="dateEnd" value="${v.getDateEnd()}">
                                                        <input type="hidden" name="promotionStatus" value="${v.isStatus()}">
                                                        <input type="hidden" name="value" value="${v.getValue()}">
                                                        <input type="hidden" name="accountID" value="${v.getAccount().getId()}">
                                                        <input type="hidden" name="accountName" value="${v.getAccount().getLastname()}">
                                                        <button style="height: 29.5px;" class="btn btn-primary btn-sm edit" type="submit" title="Sửa">
                                                            <i class="fas fa-edit"></i>
                                                        </button>
                                                    </form>


                                                </td>    

                                            </tr>
                                        <%--</c:if>--%>



                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!-- Essential javascripts for application to work-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">$('#sampleTable').DataTable();</script>
        <script>
            function confirmDelete() {
                var result = confirm("Bạn có chắc chắn muốn xóa khuyến mãi này?");
                if (!result) {
                    event.preventDefault();
                }
            }

            var messUpdatePromotion = "${messUpdatePromotion}";


            if (messUpdatePromotion !== "") {
                alert(messUpdatePromotion);
            }


            var messAddPromotion = "${messAddPromotion}";


            if (messAddPromotion !== "") {
                alert(messAddPromotion);
            }

            var messDeletePromotion = "${messDeletePromotion}";


            if (messDeletePromotion !== "") {
                alert(messDeletePromotion);
            }
        </script>
        <script>
            function filterByDate() {
                var startDate = document.getElementById("startDate").value;
                var endDate = document.getElementById("endDate").value;

                var tableRows = document.querySelectorAll("#sampleTable tbody tr");

                for (var i = 0; i < tableRows.length; i++) {
                    var row = tableRows[i];
                    var promotionStartDate = row.cells[2].textContent;
                    var promotionEndDate = row.cells[3].textContent;

                    if (promotionStartDate >= startDate && promotionEndDate <= endDate) {
                        row.style.display = "table-row";
                    } else {
                        row.style.display = "none";
                    }
                }
            }
        </script>

    </body>
</html>
