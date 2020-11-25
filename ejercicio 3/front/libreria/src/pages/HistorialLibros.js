import React from 'react';
import axios from "axios";
import { Table, Container } from "reactstrap";

const url = "http://localhost:8585/consultar-hitorial/";

class HistorialLibros extends React.Component {
    state = {
        data: []
    }
    peticionGet = () => {
        axios.get(url + Number(this.idLector)).then(response => {
            this.setState({ data: response.data });
        }).catch(error => {
            console.log(error.message);
        })
    }

    componentDidMount() {
        this.peticionGet();
    }

    render() {
        const { match } = this.props;
        this.idLector = match.params.id;
        return (
            <div>
                <Container>
                    <h1> prestados </h1>
                    <Table >
                        <thead>
                            <tr>
                                <th>Autor</th>
                                <th>Titulo</th>
                                <th>Fecha Publicacion</th>
                                <th>Genero</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.data.map(libro => {
                                return (
                                    <tr>
                                        <td>{libro.autor}</td>
                                        <td>{libro.titulo}</td>
                                        <td>{libro.fecha_publicacion}</td>
                                        <td>{libro.nombre_genero}</td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default HistorialLibros;