<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script>
            function setCookie(cname,cvalue,exdays) {
                var d = new Date();
                d.setTime(d.getTime() + (exdays*24*60*60*1000));
                var expires = "expires=" + d.toGMTString();
                document.cookie = cname+"="+cvalue+"; "+expires;
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>voistkonds</title>
    </head>
    <body onload="setCookie('token', '${token}',1/24)">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <a href='s'>Voiskonnad</a> | <a href='new'>Create new</a> <br>
        voistkonds list: 
        <br>
        <table border=1 cellpadding=2 cellspacing=1>
            <tr bgcolor='#cccccc'>
                <td><STRONG> voistkondId&nbsp;</td>
                <td><STRONG>nimetus&nbsp;</td>
                <td><STRONG>spordialaNimetus&nbsp;</td>
                <td><STRONG>riikNimetus&nbsp;</td>
                <td><STRONG>voistkonnaSeisundiLiik&nbsp;</td>
                <td><STRONG>getEMail&nbsp;</td>
                <td><STRONG>regAeg&nbsp;</td>
                <td><STRONG>kirjeldus&nbsp;</td>
                <td>link</td>
            </tr>
            <tr></tr>
            <c:forEach var="voistkond" items="${voistkonds}" >
                <TR BGCOLOR='#FFFFFF' ><TD  nowrap>
                        &nbsp${voistkond.voistkondId}&nbsp;</TD>
                    <TD>${voistkond.nimetus}&nbsp</TD>
                    <TD>${voistkond.spordialaNimetus}&nbsp</TD>
                    <TD>${voistkond.riikNimetus}&nbsp</TD>
                    <TD>${voistkond.voistkonnaSeisundiLiik}&nbsp</TD>
                    <TD>${voistkond.getEMail()}&nbsp</TD>
                    <TD>${voistkond.regAeg}&nbsp</TD>
                    <TD>${voistkond.kirjeldus}&nbsp</TD>
                    <TD>
                        <a href='javascript:showDescription("${voistkond.voistkondId}")' target='_self'>Description</a>&nbsp
                    </TD>
                    <TD align='top' nowrap>
                        <a HREF='s?id=${voistkond.voistkondId}' TARGET='_self'><b><u>change</u><b></a>
                    </TD>
                </TR>
            </c:forEach>
        </table>

        <br>
        <br>
        <div ID="ajax_response">
        </div>
        <div ID="description_form" style="visibility:hidden;">
            <form name=description_form>
                <TABLE BGCOLOR='#cccccc'>
                    <TR BGCOLOR='#ffffff'><TD BGCOLOR='#eeeeee' COLSPAN=2>Description</TD></tr>
                    <TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>voistkond id</td><td BGCOLOR='#cccccc'><input type=text name=voistkond_id size=4 disabled id='id'></TD></tr>
                    <TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap>Content</td><td BGCOLOR='#cccccc'><textarea name=description cols=25 rows=5 id="desc"></textarea></TD></tr>
                    <TR BGCOLOR='#ffffff'><TD BGCOLOR='#cccccc' nowrap COLSPAN=2><input type="button" value="HIDE" onClick="hide_description_form()"></TD></tr>
                </TABLE>
            </form>
        </div>
    </body>
</html>