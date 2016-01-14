<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Voistkonnad</title>
    </head>
    <body>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <a href='../voistkonnad/s'>Voiskonnad</a> | <a href='../voistkonnad/new'>Create new</a> | <a href='../user/logout'>Logi valja</a><br>
        voistkonds list: 
        <br>
        <table border=1 cellpadding=2 cellspacing=1>
            <tr bgcolor='#cccccc'>
                <td><STRONG> teamId&nbsp;</td>
                <td><STRONG>name&nbsp;</td>
                <td><STRONG>sport&nbsp;</td>
                <td><STRONG>country&nbsp;</td>
                <td><STRONG>state&nbsp;</td>
                <td><STRONG>getEMail&nbsp;</td>
                <td><STRONG>regDate&nbsp;</td>
                <td><STRONG>description&nbsp;</td>
                <td><STRONG>changer&nbsp;</td>
                <td>link</td>
            </tr>
            <tr></tr>
            <c:forEach var="voistkond" items="${voistkonds}" >
                <TR BGCOLOR='#FFFFFF' ><TD  nowrap>
                        &nbsp${voistkond.teamId}&nbsp;</TD>
                    <TD>${voistkond.name}&nbsp</TD>
                    <TD>${voistkond.sport}&nbsp</TD>
                    <TD>${voistkond.country}&nbsp</TD>
                    <TD>${voistkond.state}&nbsp</TD>
                    <TD>${voistkond.getEMail()}&nbsp</TD>
                    <TD>${voistkond.regDate}&nbsp</TD>
                    <TD>${voistkond.description}&nbsp</TD>
                    <TD>${voistkond.changer}&nbsp</TD>
                    <TD align='top' nowrap>
                        <a HREF='s?id=${voistkond.teamId}' TARGET='_self'><b><u>change</u><b></a>
                    </TD>
                </TR>
            </c:forEach>
        </table>

        <br>
        <br>
    </body>
</html>