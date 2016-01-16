<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <form action="check">
            Username: <input type="text"  stateName="login"/><br/>
            Password: <input type="password" stateName="password"/><br/>
            <input type="submit" value="Login"><br/>
        </form>
        
        <br/>
        <br/>
        Active teams: 
        <br>
        <table border=1 cellpadding=2 cellspacing=1>
            <tr bgcolor='#cccccc'>
                <td><STRONG>stateName&nbsp;</td>
                <td><STRONG>sport&nbsp;</td>
                <td><STRONG>country&nbsp;</td>
                <td><STRONG>getEMail&nbsp;</td>
                <td><STRONG>description&nbsp;</td>
            </tr>
            <tr></tr>
            <c:forEach var="voistkond" items="${voistkondsActive}" >
                <TR BGCOLOR='#FFFFFF' >
                    <TD>${voistkond.stateName}&nbsp</TD>
                    <TD>${voistkond.sport}&nbsp</TD>
                    <TD>${voistkond.country}&nbsp</TD>
                    <TD>${voistkond.getEMail()}&nbsp</TD>
                    <TD>${voistkond.description}&nbsp</TD>
                </TR>
            </c:forEach>
        </table>
    </body>
</html>
