#include <FileConstants.au3>
#include <File.au3>

_RecDelete("*.exe", @WorkingDir)
_RecDelete("*.o", @WorkingDir)

Func _RecDelete($filter, $path)
	$folders = _FileListToArray($path, "*", $FLTA_FOLDERS, True)
	If IsArray($folders) Then
		For $i = 1 To $folders[0]
			_RecDelete($filter, $folders[$i])
		Next
	EndIf

	$files = _FileListToArray($path, $filter, $FLTA_FILES, True)
	If IsArray($files) Then
		For $i = 1 To $files[0]
			FileDelete($files[$i])
		Next
	EndIf
EndFunc