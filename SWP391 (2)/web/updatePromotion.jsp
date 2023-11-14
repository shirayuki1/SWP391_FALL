<%-- 
    Document   : updatePromotion
    Created on : Oct 8, 2023, 4:17:21 PM
    Author     : Admin
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Sửa khuyến mãi</title>
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
                    <li class="breadcrumb-item"><a href="promotion">Danh sách khuyến mãi</a></li>
                    <li class="breadcrumb-item"><a href="#">Sửa khuyến mãi</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Sửa khuyến mãi</h3>
                        <div class="tile-body">
                            <form action="UpdatePromotion" class="row">

                                <div class="form-group  col-md-4">
                                    <label class="control-label">ID khuyến mãi</label>
                                    <input readonly="" class="form-control" type="number" value="${promotionID}" disabled>
                                    <input name="promotionID" readonly="" class="form-control" type="hidden" value="${promotionID}">
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Tên khuyến mãi</label>
                                    <input required="" name="promotionName" class="form-control" type="text" value="${promotionName}">

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày bắt đầu</label>
                                    <input required="" name="dateStart" class="form-control" type="date" value="${dateStart}" >

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày kết thúc</label>
                                    <input required="" name="dateEnd" class="form-control" type="date" value="${dateEnd}" >

                                </div>
<!--                                <div class="form-group col-md-4">
                                    <div>
                                        <label class="control-label">Trạng thái</label>
                                    </div>
                                    <div>
                                        <label class="toggle-switch">
                                            <input type="checkbox" name="promotionStatus" <% if ((boolean) request.getAttribute("promotionStatus")) { %>checked<% }%> value="true">
                                            <div class="toggle-switch-background">
                                                <div class="toggle-switch-handle"></div>
                                            </div>
                                        </label>
                                    </div>
                                </div>-->

<!--                                <div class="form-group  col-md-4">
                                    <label class="control-label">Điều kiện</label>
                                    <input required="" name="condition" class="form-control" type="number" value="${condition}">

                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Số lượng</label>
                                    <input required="" name="quantity" class="form-control" type="number" value="${quantity}">
                                </div>-->

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Giá trị</label>
                                    <input required="" name="value" class="form-control" type="number" value="${value}">
                                </div>

                                
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Người tạo</label>
                                                                        <!-- Display the last name to the user, but submit the account ID -->
                                    <input class="form-control" type="text" id="lastnameInput" value="${requestScope.accName}">

                                    <!-- Hidden input field for submitting the account ID -->
                                    <input type="hidden" name="accountID" id="accountIDInput" value="${requestScope.accID}">

                                </div>
                                    
                                


                        </div>
                        <button class="btn btn-save" type="submit">Lưu lại</button>
                        <a class="btn btn-cancel" href="promotion">Hủy bỏ</a>           

                    </div>
                </div>
            </div>


        </main>
        <script>
            // Get the dateEnd, dateStart, promotionStatus, quantity, value, and condition elements
            var dateEndInput = document.querySelector('input[name="dateEnd"]');
            var dateStartInput = document.querySelector('input[name="dateStart"]');
            var promotionStatusCheckbox = document.querySelector('input[name="promotionStatus"]');
            var quantityInput = document.querySelector('input[name="quantity"]');
            var valueInput = document.querySelector('input[name="value"]');
            var conditionInput = document.querySelector('input[name="condition"]');

            // Add an event listener to the form submission
            document.querySelector('form').addEventListener('submit', function (event) {
                // Get the selected promotionStatus value
                var promotionStatus = promotionStatusCheckbox.checked;

                // Get the current date
                var currentDate = new Date().toISOString().split('T')[0];

                // Get the entered dateEnd, dateStart, quantity, value, and condition values
                var dateEnd = dateEndInput.value;
                var dateStart = dateStartInput.value;
                var quantity = parseInt(quantityInput.value);
                var value = parseInt(valueInput.value);
                var condition = parseInt(conditionInput.value);

                // Perform the validation
                var errors = []; // To store validation errors

                if (promotionStatus && dateEnd < currentDate) {
                    event.preventDefault(); // Prevent form submission
                    alert('Ngày kết thúc không hợp lệ! Ngày kết thúc phải lớn hơn hoặc bằng ngày hôm nay khi trạng thái ON.');
                }

                if (promotionStatus && dateStart > currentDate) {
                    event.preventDefault(); // Prevent form submission
                    alert('Ngày bắt đầu không hợp lệ! Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày hôm nay khi trạng thái ON.');
                }

                if (condition <= 0) {
                    errors.push('Điều kiện không hợp lệ! Điều kiện phải lớn hơn 0.');
                }

                if (quantity <= 0) {
                    errors.push('Số lượng không hợp lệ! Số lượng phải lớn hơn 0.');
                }

                if (value <= 0) {
                    errors.push('Giá trị không hợp lệ! Giá trị phải lớn hơn 0.');
                }

                if (dateEnd < dateStart) {
                    errors.push('Ngày bắt đầu và kết thúc không hợp lệ! Ngày kết thúc phải lớn hơn hoặc bằt ngày bắt đầu.');
                }

                // Check if any errors occurred
                if (errors.length > 0) {
                    event.preventDefault(); // Prevent form submission

                    // Display the error messages
                    alert(errors.join('\n'));
                }
            });
        </script>

    </body>
</html>
