
<style>
    html {
        font-family: sans-serif;
    }

    table {
        border-collapse: collapse;
        border: 2px solid rgb(200,200,200);
        letter-spacing: 1px;
        font-size: 0.8rem;
    }

    td, th {
        border: 1px solid rgb(190,190,190);
        padding: 10px 20px;
    }

    th {
        background-color: rgb(235,235,235);
    }

    td {
        text-align: center;
    }

    tr:nth-child(even) td {
        background-color: rgb(250,250,250);
    }

    tr:nth-child(odd) td {
        background-color: rgb(245,245,245);
    }

    caption {
        padding: 10px;
    }
</style>
<h1>Registro de accesos</h1>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Hora de entrada</th>
            <th>Porteria</th>
            <th>Patente</th>
        </tr>
    </thead>
    <tbody>
    @foreach($accesos as $acceso)
        <tr>
            <td>{{$acceso->uid}}</td>
            <td>{{$acceso->horaEntrada}}</td>
            <td>
                @switch($acceso->porteria)
                    @case(0)
                        SANGRA
                        @break
                    @case(1)
                         SUR
                        @break
                    @case(2)
                        CERRO
                        @break
                @endswitch

            </td>
            <td>{{$acceso->patente}}</td>
        </tr>
    @endforeach
    </tbody>
</table>

