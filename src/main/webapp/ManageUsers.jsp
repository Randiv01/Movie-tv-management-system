<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    HttpSession sessionObj = request.getSession(false);

    Admin loggedInAdmin = null;
    if (sessionObj != null) {
        loggedInAdmin = (Admin) sessionObj.getAttribute("admin");
    }

    if (loggedInAdmin == null) {
        response.sendRedirect("Login.jsp");  // Redirect if not logged in
        return;
    }

    // Optional: Get admin ID or other info if needed
    int adminId = loggedInAdmin.getAid();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" type="text/css" href="CSS/ManageUser.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="AdminHeader.jsp" %>

    <div class="admin-user-management">
        <div class="page-title">
            <h2><i class="fas fa-users"></i> User Management Portal</h2>
        </div>
        <br>

        <h3>All Registered Users</h3>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Country</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Mobile</th>
                    <th>Profile Image</th>
                    <th>NIC</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
               <c:forEach var="user" items="${userList}">
    <tr>
        <td><input form="form${user.id}" type="text" name="id" value="${user.id}" readonly /></td>
        <td><input form="form${user.id}" type="text" name="fname" value="${user.fname}" /></td>
        <td><input form="form${user.id}" type="text" name="lname" value="${user.lname}" /></td>
        <td><input form="form${user.id}" type="email" name="gmail" value="${user.gmail}" /></td>
        <td><input form="form${user.id}" type="password" name="password" value="${user.password}" /></td>
        <td><input form="form${user.id}" type="text" name="country" value="${user.country}" /></td>
        <td>
		    <select form="form${user.id}" name="gender" class="gender-select">
		        <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
		        <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
		        <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
		    </select>
		</td>
        <td><input form="form${user.id}" type="date" name="dob" value="${user.dob}" /></td>
        <td><input form="form${user.id}" type="text" name="mobile" value="${user.mobile}" /></td>
        <td>
            <c:choose>
                <c:when test="${not empty user.profileimage}">
                    <img src="ProfileImages/${user.profileimage}" alt="Profile Image" class="profile-img"/>
                    <input form="form${user.id}" type="hidden" name="oldImage" value="${user.profileimage}" />
                    <input form="form${user.id}" type="file" name="profileImage" accept="image/*" />
                </c:when>
                <c:otherwise>
                    <i class="fas fa-user-circle fa-2x"></i>
                    <input form="form${user.id}" type="file" name="profileImage" accept="image/*" />
                </c:otherwise>
            </c:choose>
        </td>
        <td><input form="form${user.id}" type="text" name="nic" value="${user.nic}" /></td>
        <td><input form="form${user.id}" type="text" name="address" value="${user.address}" /></td>
        <td>
            <form id="form${user.id}" action="AlluserUpdateServlet" method="post" enctype="multipart/form-data">
                <button type="submit" name="action" value="update" class="action-btn edit-btn">Update</button>
                <a href="AllUserDeleteServlet?id=${user.id}" class="action-btn delete-btn" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
            </form>
        </td>
    </tr>
</c:forEach>



                <c:if test="${empty userList}">
                    <tr><td colspan="13">No users found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <%@ include file="AdminFooter.jsp" %>
</body>
</html>
