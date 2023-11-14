<%-- 
    Document   : addPromotion
    Created on : Oct 8, 2023, 2:37:13 PM
    Author     : Admin
--%>
<%@page import="Model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tạo promotion</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="css/view.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
        <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
        <style>
            .toggle-switch {
                position: relative;
                display: inline-block;
                width: 80px;
                height: 40px;
                cursor: pointer;
            }

            .toggle-switch input[type="checkbox"] {
                display: none;
            }

            .toggle-switch-background {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: #ddd;
                border-radius: 20px;
                box-shadow: inset 0 0 0 2px #ccc;
                transition: background-color 0.3s ease-in-out;
            }

            .toggle-switch-handle {
                position: absolute;
                top: 5px;
                left: 5px;
                width: 30px;
                height: 30px;
                background-color: #fff;
                border-radius: 50%;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                transition: transform 0.3s ease-in-out;
            }

            .toggle-switch::before {
                content: "";
                position: absolute;
                top: -25px;
                right: -35px;
                font-size: 12px;
                font-weight: bold;
                color: #aaa;
                text-shadow: 1px 1px #fff;
                transition: color 0.3s ease-in-out;
            }

            .toggle-switch input[type="checkbox"]:checked + .toggle-switch-handle {
                transform: translateX(45px);
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2), 0 0 0 3px #05c46b;
            }

            .toggle-switch input[type="checkbox"]:checked + .toggle-switch-background {
                background-color: #05c46b;
                box-shadow: inset 0 0 0 2px #04b360;
            }

            .toggle-switch input[type="checkbox"]:checked + .toggle-switch:before {
                content: "On";
                color: #05c46b;
                right: -15px;
            }

            .toggle-switch input[type="checkbox"]:checked + .toggle-switch-background .toggle-switch-handle {
                transform: translateX(40px);
            }

        </style>
    </head>
    <body class="app sidebar-mini rtl">
        
        <!--Check login-->

        <!-- Navbar-->
        <jsp:include page="managerpage.jsp"/>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="promotion">Danh sách Promotion</a></li>
                    <li class="breadcrumb-item"><a href="addPromotion.jsp">Tạo Promotion</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Tạo Promotion</h3>
                        <div class="tile-body">
                            <form action="AddPromotion" class="row" method="post" onsubmit="return validateForm()">

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Tên khuyến mãi</label>
                                    <input name="promotionName" class="form-control" type="text" id="name">
                                    <div class="error-message" id="name-error"></div>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày bắt đầu</label>
                                    <input name="dateStart" class="form-control" type="date" id="datestart">
                                    <div class="error-message" id="datestart-error"></div>

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày kết thúc</label>
                                    <input name="dateEnd" class="form-control" type="date" id="dateend" >
                                    <div class="error-message" id="dateend-error"></div>

                                </div>
<!--                                <div class="form-group  col-md-4">
                                    <label class="control-label">Trạng thái</label>
                                    <div>
                                        <label class="toggle-switch">
                                            <input type="checkbox" name="promotionStatus" value="true">
                                            <div class="toggle-switch-background">
                                                <div class="toggle-switch-handle"></div>
                                            </div>
                                        </label>
                                    </div>
                                </div>-->
<!--                                <div class="form-group  col-md-4">
                                    <label class="control-label">Điều kiện</label>
                                                                        <input name="condition" class="form-control" type="number">
                                    <input required="" name="condition" class="form-control" type="number" />


                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Số lượng</label>
                                    <input required="" name="quantity" class="form-control" type="number">
                                </div>-->

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Giá trị</label>
                                    <input name="value" class="form-control" type="number" id="value">
                                    <div class="error-message" id="value-error"></div>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Điều kiện</label>
                                    <input name="condition" class="form-control" type="number" id="condition">
                                    <div class="error-message" id="condition-error"></div>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Người tạo</label>
                                                                        <!-- Display the last name to the user, but submit the account ID -->
                                    <input class="form-control" type="text" id="lastnameInput" value="${requestScope.user.lastname}">

                                    <!-- Hidden input field for submitting the account ID -->
                                    <input type="hidden" name="accountID" id="accountIDInput" value="${requestScope.user.getId()}">

                                </div>


                        </div>
                        <button class="btn btn-save" type="submit">Lưu lại</button>
                        <a class="btn btn-cancel" href="promotion">Hủy bỏ</a>           

                    </div>
                </div>
            </div>


        </main>
        <script>
                        function validateForm() {
                            var tensp = document.getElementById("name").value.trim();
                            var condition = document.getElementById("condition").value.trim();
                            var value = document.getElementById("value").value.trim();
                            var datestart = document.getElementById("datestart").value.trim();
                            var dateend = document.getElementById("dateend").value.trim();
                            

                            var isValid = true;

                            if (tensp === "") {
                                document.getElementById("name").classList.add("error-input");
                                document.getElementById("name-error").textContent = "Vui lòng nhập tên khuyến mãi.";
                                document.getElementById("name-error").style.color = "red";
                                document.getElementById("name").style.borderColor = "red";
                                isValid = false;
                            } else {
                                document.getElementById("name").classList.remove("error-input");
                                document.getElementById("name-error").textContent = "";
                                document.getElementById("name-error").style.color = "";
                                document.getElementById("name").style.borderColor = "";
                            }

                            if (condition === "") {
                                document.getElementById("condition").classList.add("error-input");
                                document.getElementById("condition-error").textContent = "Vui lòng nhập điều kiện.";
                                document.getElementById("condition-error").style.color = "red";
                                document.getElementById("condition").style.borderColor = "red";
                                isValid = false;
                            } else if(condition <0){
                                 document.getElementById("condition").classList.add("error-input");
                                document.getElementById("condition-error").textContent = "Điều kiện vui lòng lớn hơn 0.";
                                document.getElementById("condition-error").style.color = "red";
                                document.getElementById("condition").style.borderColor = "red";
                                isValid = false;
                                
                            }
                            else {
                                document.getElementById("condition").classList.remove("error-input");
                                document.getElementById("condition-error").textContent = "";
                                document.getElementById("condition-error").style.color = "";
                                 document.getElementById("condition").style.borderColor = "";
                            }

                            if (value === "") {
                                document.getElementById("value").classList.add("error-input");
                                document.getElementById("value-error").textContent = "Vui lòng nhập giá trị.";
                                document.getElementById("value-error").style.color = "red";
                                document.getElementById("value").style.borderColor = "red";
                                isValid = false;
                            }else if (value < 0 || value > 100){
                                document.getElementById("value").classList.add("error-input");
                                document.getElementById("value-error").textContent = " Giá trị vui lòng lớn hơn 0 và nhỏ hơn 100.";
                                document.getElementById("value-error").style.color = "red";
                                document.getElementById("value").style.borderColor = "red";
                                isValid = false;
                                
                            } 
                            else {
                                document.getElementById("value").classList.remove("error-input");
                                document.getElementById("value-error").textContent = "";
                                document.getElementById("value-error").style.color = "";
                                 document.getElementById("value").style.borderColor = "";
                            }
                            
                            if (datestart === "") {
                                document.getElementById("datestart").classList.add("error-input");
                                document.getElementById("datestart-error").textContent = "Vui lòng nhập ngày bắt đầu.";
                                document.getElementById("datestart-error").style.color = "red";
                                document.getElementById("datestart").style.borderColor = "red";
                                isValid = false;
                            } 
                            else {
                                document.getElementById("datestart").classList.remove("error-input");
                                document.getElementById("datestart-error").textContent = "";
                                document.getElementById("datestart-error").style.color = "";
                                 document.getElementById("datestart").style.borderColor = "";
                            }


                            if (dateend === "") {
                                document.getElementById("dateend").classList.add("error-input");
                                document.getElementById("dateend-error").textContent = "Vui lòng nhập ngày kết thúc.";
                                document.getElementById("dateend-error").style.color = "red";
                                document.getElementById("dateend").style.borderColor = "red";
                                isValid = false;
                            } 
                            else {
                                document.getElementById("dateend").classList.remove("error-input");
                                document.getElementById("dateend-error").textContent = "";
                                document.getElementById("dateend-error").style.color = "";
                                 document.getElementById("dateend").style.borderColor = "";
                            }
                            
                            if(datestart > dateend){
                                isValid = false;
                                document.getElementById("datestart-error").style.color = "red";
                                document.getElementById("datestart").style.borderColor = "red";
                                document.getElementById("dateend-error").style.color = "red";
                                document.getElementById("dateend").style.borderColor = "red";
                                alert("Ngày bắt đầu phải bé hơn hoặc cùng ngày kết thúc");
                            }

                            return isValid;
                        }
                    </script>

    </body>
</html>
