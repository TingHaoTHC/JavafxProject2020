function Get-FolderHash ($folder) {
 dir $folder -Recurse | ?{!$_.psiscontainer} | %{[Byte[]]$contents += [System.IO.File]::ReadAllBytes($_.fullname)}
 $hasher = [System.Security.Cryptography.SHA1]::Create()
 [string]::Join("",$($hasher.ComputeHash($contents) | %{"{0:x2}" -f $_}))
}

Get-FolderHash out