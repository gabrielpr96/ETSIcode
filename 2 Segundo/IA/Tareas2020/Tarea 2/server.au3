#include <Constants.au3>
#include <Array.au3>

Const $exe = "Minmax4EnRaya.exe"
Const $timeOut = 10000
Const $FILAS = 6
Const $COLUMNAS = 7

TCPStartup()
$socket = TCPListen("0.0.0.0",8082)
While 1
   Do
	  $newConnection = TCPAccept($socket)
	  Sleep(1)
   Until $newConnection <> -1
   $content = ""
   $content = TCPRecv($newConnection, 2048)

	$state = StringMid($content, 6, StringInStr($content, "/", 2, 5)-6)
	$part = StringSplit($state, "/")

	If $part[0] <> 4 Then
		TCPCloseSocket($newConnection)
		ContinueLoop
	EndIf
	$out = "<h1>C4</h1><h4>That is where you are wrong because where you see one man I C4. BOOOM!!!</h4><hr>"

	If($part[1] = "a") Then
		If $part[3] <= 8 Then
			$out &= dibujar($part[3])
		Else
			$out &= "<h1>No permito una profundidad superior a 8</h1>"
			$out &= '<button onclick="location.href='&"'/'"&'" type="button">Jugar de nuevo</button>'
		EndIf
	ElseIf($part[1] = "b") Then
		$res = __runWaitGet($exe&" "&$part[2]&" "&$part[3]&" "&$part[4], false)
		If $res == -1 Then
			$out &= "<h1>Tiempo limite superado</h1>"
		Else
			$res = StringSplit($res, "|")
			If $res[0] <> 2 Then
				TCPCloseSocket($newConnection)
				ContinueLoop
			EndIf
			$out &= dibujar($part[3], $res[1], $res[2] == 1)
			If $res[2] == 2 Then
				$out &="<hr><h2>Ha ganado el humano</h2>"
				;TODO: Aumentar el fichero de humano
				FileWrite("humano.txt", $part[3]&@CRLF)
			ElseIf $res[2] == 3 Then
				$out &="<hr><h2>Ha ganado la maquina</h2>"
				;TODO: Aumentar el fichero de maquina
				FileWrite("maquina.txt", $part[3]&@CRLF)
			EndIf
			If $res[2] <> 1 Then
				$out &='<button onclick="location.href='&"'/'"&'" type="button">Jugar de nuevo</button>'
			EndIf
		EndIf
	Else
		$out &= 'Profundidad: <input type="text" id="profundidad"><br></form><br><button onclick="comenzar()" type="button">Comenzar</button><script>function comenzar(){location.href="/a"+"/-/"+document.getElementById("profundidad").value+"/-/";}</script>'
	EndIf

   If StringLen($content) > 0 Then
	  TCPSend($newConnection, Binary("HTTP/1.1 200 OK" & @CRLF))
	  TCPSend($newConnection, Binary("Content-Type: text/html" & @CRLF))
	  TCPSend($newConnection, Binary("Content-Length: "& BinaryLen($out) & @CRLF & @CRLF))
	  TCPSend($newConnection, $out)
   EndIf
   TCPCloseSocket($newConnection)
   ;Exit 1
WEnd






Func dibujar($lvl, $data = "------------------------------------------", $continue = True)
	$data = StringReplace($data, "-", " ")
	$text = "<table>"
	local $t[$COLUMNAS][$FILAS]

	For $c = 0 To $COLUMNAS-1
		For $f = 0 To $FILAS-1
			$t[$c][$f] = StringMid($data, $f+$c*$FILAS+1, 1)
		Next
	Next
	$data = StringReplace($data, " ", "-")

	For $f = 0 To $FILAS-1
		$text &= '<tr>'
		For $c = 0 To $COLUMNAS-1
			$text &= '<td><input type="text" disabled="true" size="1" style="text-align:center;" value="'&$t[$c][$f]&'"></td>'
		Next
		$text &= '</tr>'
	Next

	If $continue Then
		$text &= '<tr>'
		For $c = 0 To $COLUMNAS-1
			$text &= '<td><button onclick="set'&$c&'()" type="button" size="1" style="display: block;text-align: center;width: 100%;">'&($c+1)&'</button></td>'
		Next
		$text &= '</tr>'

		$text &= '<script>'
		For $c = 0 To $COLUMNAS-1
			$text &= 'function set'&$c&'(){location.href="/b/'&$data&'/'&$lvl&'/'&$c&'/"}'
		Next
		$text &= '</script>'
	EndIf

	return $text
EndFunc


Func __runWaitGet($execute, $show = true, $workingdir = @ScriptDir)
	Local $timer = TimerInit()

	$pid = Run($execute, $workingdir, $show?@SW_SHOW:@SW_HIDE, BitOR($STDERR_CHILD, $STDOUT_CHILD))

    Local $output = ""
    While 1
        $output &= StdoutRead($pid)
        If @error Or TimerDiff($timer) > $timeOut Then ExitLoop
    WEnd
    While 1
        $output &= StderrRead($pid)
        If @error Or TimerDiff($timer) > $timeOut Then ExitLoop
	WEnd

	If ProcessExists($pid) Then
		ProcessClose($pid)
		Return -1
	EndIf

    Return $output
EndFunc