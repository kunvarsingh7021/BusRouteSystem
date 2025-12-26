<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shortest Path Finder</title>

    <!-- FONT AWESOME (ONLINE ICONS) -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="stylesheet" href="style.css">

    <style>
        .info {
            margin-top: 15px;
            color: #475569;
            font-size: 14px;
        }
    </style>
</head>

<body>

<h2>
    <i class="fa-solid fa-route"></i>
    Shortest Path Finder
</h2>

<form action="shortest" method="post" class="card">
    <input name="src" placeholder="Source Stop" required>
    <input name="dest" placeholder="Destination Stop" required>

    <button>
        <i class="fa-solid fa-magnifying-glass-location"></i>
        Find Shortest Path
    </button>
</form>

<p class="info">
    <i class="fa-solid fa-circle-info"></i>
    Result will be displayed below inside this module
</p>

<!-- NO BACK LINK, NO DASHBOARD -->

</body>
</html>
