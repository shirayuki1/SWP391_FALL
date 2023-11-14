<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html >
    <head>
        <meta charset="utf-8">
        <link rel="png" href="logo.png" type="img/logo.png">
        <title>LồngChimVN</title>      
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
        <!-- Libraries Stylesheet -->
        <link rel="stylesheet" type="text/css" href="./css/cart.css">

        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->

        <link href="css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.1/css/jquery.dataTables.min.css">
    </head>

    <body> 
        <jsp:include page="headerUser.jsp"/>
        <!--Products Start--> 
        <div class="container-fluid pt-5 pb-3">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Thanh Toán</span></h2>



           
            
            <section class="h-100 h-custom" style="background-color: #d2c9ff;">
                <div class="container py-5 h-100">
                    <c:if test="${sessionScope.cart != null && sessionScope.cart.size() > 0}">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col-12">
                                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                                    <div class="card-body p-0">
                                        <div class="row g-0">
                                            <div class="col-lg-8">
                                                <div class="p-5 ">
                                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                                        <h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
                                                        <h6 class="mb-0 text-muted">3 items</h6>
                                                    </div>
                                                    <hr class="my-4">

                                                    <div class="row mb-4 d-flex justify-content-between align-items-center">
                                                        <c:forEach items="${sessionScope.cart}" var="item">

                                                            <form action="CartServlet" method="post" class="row mb-4 d-flex justify-content-between align-items-center" style="border-bottom: 1px solid #ccc;    padding: 15px 0;">
                                                                <hr class="my-4">
                                                                <input type="hidden" name="action" value="update" />
                                                                <input type="hidden" name="pid" value="${item.key.getProductID()}" />
                                                                <div class="col-md-2 col-lg-2 col-xl-2">
                                                                    <img src="img/${item.key.getImage()}"
                                                                         class="img-fluid rounded-3" alt="Cotton T-shirt">
                                                                </div>
                                                                <div class="col-md-3 col-lg-3 col-xl-3">
                                                                    <h6 class="text-muted">ID: ${item.key.getProductID()}</h6>
                                                                    <h6 class="text-black mb-0">${item.key.getName()}</h6>
                                                                </div>
                                                                <div class="col-md-5 col-lg-3 col-xl-2 d-flex">
                                                                    <button class="btn btn-link px-2"
                                                                            onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                                        <i class="fas fa-minus"></i>
                                                                    </button>

                                                                    <input id="form1" min="1" name="num" value="${item.value}" type="number"
                                                                           class="form-control form-control-sm"  style="width: calc(2.5em + 0.5rem + 2px);"/>

                                                                    <button class="btn btn-link px-2"
                                                                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                                        <i class="fas fa-plus"></i>
                                                                    </button>
                                                                </div>
                                                                <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                                    <h6 class="mb-0">${item.key.getPrice()} đ</h6>
                                                                </div>

                                                                <!--                                                            <button type="submit" class="btn btn-primary">
                                                                                                                                <i class="fas fa-check-circle"></i>
                                                                                                                            </button>-->
                                                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                    <a href="${pageContext.request.contextPath}/delete" class="text-muted"><i class="fas fa-times"></i></a>
                                                                </div>

                                                            </form>


                                                        </c:forEach>
                                                        <hr class="my-4">

                                                    </div>

                                                    <div class="pt-5">
                                                        <h6 class="mb-0"><a href="ShowProductsServlet" class="text-body"><i class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 bg-grey">
                                                <div class="p-5" style="padding: 2rem !important;">
                                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                                    <hr class="my-4">
                                                    <div class="d-flex justify-content-between mb-4">
                                                        <h5 class="text-uppercase">items 3</h5>

                                                    </div>
                                                    <h5 class="text-uppercase mb-3">Shipping :Cash On Delivery </h5>                                             

                                                    <c:if test="${p != null}">
                                                        <h5 class="text-uppercase mb-3">Promotion code</h5>
                                                        <div class="mb-5">
                                                            <div class="form-outline">
                                                                <input type="text" id="form3Examplea2" class="form-control form-control-lg" value="${p.name} - ${p.value} % off" readonly />
                                                            </div>
                                                        </div>
                                                    </c:if> 
                                                        
                                                    <div class="d-flex justify-content-between mb-5">
                                                        <h5 class="text-uppercase">Total price:</h5>
                                                        <h5 id="totalPrice">${requestScope.totalPrice} VND</h5>
                                                    </div>

                                                    <hr class="my-4">
                                                    
                                                    <c:if test="${p != null}">
                                                        <div class="d-flex justify-content-between mb-5">
                                                            <h5 class="text-uppercase">Discount Price:</h5>
                                                            <h5 id="discountPrice">${requestScope.totalPrice * (100 - p.value) / 100} VND</h5>
                                                        </div>
                                                    </c:if>
                                                    
                                                    

                                                    <a href="checkout" class="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark">Checkout</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.cart == null || sessionScope.cart.size() == 0}">
                        <div class="alert alert-warning">Bạn Không Có Đơn Hàng Nào.</div>
                    </c:if>
                </div>
            </section>

        </div>
        <!--Products End--> 


        <!--            <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                            </li>
                            <li class="page-item active" aria-current="page">
                                <a class="page-link" href="#">1 <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">Next</a>
                            </li>
                        </ul>
                    </nav>-->









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


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>


        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#productTable').DataTable({
                    "paging": true,
                    "pageLength": 20,
                    "lengthMenu": [20, 50, 100],
                    "language": {
                        "lengthMenu": "Display _MENU_ products per page",
                        "zeroRecords": "No products found",
                        "info": "Showing page _PAGE_ of _PAGES_",
                        "infoEmpty": "No products available",
                        "infoFiltered": "(filtered from _MAX_ total products)"
                    }
                });
            });
        </script>
        
        
         <script>
                                                                            // Function to validate and update the price
                                                                            function updatePrice() {
                                                                                var discountPriceElement = document.getElementById("discountPrice");
                                                                                var totalPriceElement = document.getElementById("totalPrice");

                                                                                var discountValue = parseFloat("${p.value}");
                                                                                var totalPrice = parseFloat("${requestScope.totalPrice}");

                                                                                if (!isNaN(discountValue) && !isNaN(totalPrice)) {
                                                                                    var discountedPrice = totalPrice * (100 - discountValue) / 100;
                                                                                    discountPriceElement.textContent =  discountedPrice.toFixed(0) + " đ";
                                                                                    totalPriceElement.textContent =  totalPrice.toFixed(0) + " đ";
                                                                                } else {
                                                                                    discountPriceElement.textContent = "Invalid Input";
                                                                                    totalPriceElement.textContent = "Invalid Input";
                                                                                }
                                                                            }

                                                                            // Call the updatePrice function when the page loads
                                                                            updatePrice();
            </script>


            
            <script>
                var itemCount = 0; // Initialize item count
                var totalPrice = 0; // Initialize total price

                <c:forEach items="${sessionScope.cart}" var="item">
                // Inside the loop, update item count and total price
                itemCount += 1;
                totalPrice += ${item.key.getPrice()} * ${item.value};
                </c:forEach>

                // Update the HTML elements with the calculated values
                document.addEventListener("DOMContentLoaded", function () {
                    var itemText = itemCount === 1 ? 'item' : 'items'; // Pluralize "item" if necessary
                    document.querySelector('h6.text-muted').textContent = itemCount + ' ' + itemText;
                    document.querySelector('h5.text-uppercase').textContent = 'items ' + itemCount;
                    document.querySelector('h5.text-uppercase').textContent = 'items ' + itemCount;
//                    document.querySelector('h5').textContent = 'ToTal: $' + totalPrice.toFixed(2); // Format the total price
                });
            </script>
        <script>
            function addToCart(productId, productName, price, quantity, imageUrl) {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "CartServlet", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        // Xử lý phản hồi thành công
                        window.location.reload(); // Tải lại trang để cập nhật giỏ hàng
                    }
                };
                xhr.send("action=add&productId=" + encodeURIComponent(productId) +
                        "&productName=" + encodeURIComponent(productName) +
                        "&price=" + encodeURIComponent(price) +
                        "&quantity=" + encodeURIComponent(quantity) +
                        "&imageUrl=" + encodeURIComponent(imageUrl));
            }
        </script>




        <script type="text/javascript">
            $(function () {
                $('#example-table').DataTable({
                    pageLength: 10,
                    //"ajax": './assets/demo/data/table_data.json',
                    /*"columns": [
                     { "data": "name" },
                     { "data": "office" },
                     { "data": "extn" },
                     { "data": "start_date" },
                     { "data": "salary" }
                     ]*/
                });
            })
        </script>


    </body>

</html>