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
            Username: <input type="text"  name="login"/><br/>
            Password: <input type="password" name="password"/><br/>
            <input type="submit" value="Login"><br/>
        </form>
        
        <br/>
        <br/>
        Active teams: 
        <br>
        <table border=1 cellpadding=2 cellspacing=1>
            <tr bgcolor='#cccccc'>
                <td><STRONG>nimetus&nbsp;</td>
                <td><STRONG>spordialaNimetus&nbsp;</td>
                <td><STRONG>riikNimetus&nbsp;</td>
                <td><STRONG>getEMail&nbsp;</td>
                <td><STRONG>kirjeldus&nbsp;</td>
            </tr>
            <tr></tr>
            <c:forEach var="voistkond" items="${voistkondsActive}" >
                <TR BGCOLOR='#FFFFFF' >
                    <TD>${voistkond.nimetus}&nbsp</TD>
                    <TD>${voistkond.spordialaNimetus}&nbsp</TD>
                    <TD>${voistkond.riikNimetus}&nbsp</TD>
                    <TD>${voistkond.getEMail()}&nbsp</TD>
                    <TD>${voistkond.kirjeldus}&nbsp</TD>
                </TR>
            </c:forEach>
        </table>
    </body>
</html>
