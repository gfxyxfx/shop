<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
<aside id="menu">
    <div id="navigation">
        <div class="profile-picture">
            <a href="#">
                <img style="width: 150px" src="${pageContext.request.contextPath}/resources/images/logo.gif" class="m-b" alt="logo">
            </a>
        </div>
        <ul class="nav" id="side-menu">
            <li>
                <a href="${pageContext.request.contextPath}/admin/productCategoryList"><span class="nav-label">商品分类管理</span></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/productList"><span class="nav-label">商品管理</span></a>
            </li>
        </ul>
    </div>
</aside>