import React from 'react';
import axios from "axios";
import { Link } from 'react-router-dom';

import { Table, Button, Container, Modal, ModalHeader, ModalBody, ModalFooter, } from "reactstrap";

const url = "http://localhost:8282/mostrar-libros-diponibles";

class SolicitarLibro extends React.Component {
    state = {
        data: [],
        modalActualizar: false,
        modalEliminar: false,
        form: {
            id: '',
            nombre: '',
            correo: ''
        },
        typeStatus: 1
    }

    peticionGet = () => {
        axios.get(url).then(response => {
            this.setState({ data: response.data });
        }).catch(error => {
            console.log(error.message);
        })
    }


    peticionGetLector = () => {
        axios.get("http://localhost:8585/consultar-lector/" + this.idLector).then(response => {
            if (response.data.length === 0) {
                alert("usuario dado de baja");
            } else {
                this.setState({
                    form: {
                        id: response.data.id,
                        nombre: response.data.nombre,
                        correo: response.data.correo,
                        fecha_nacimiento: response.data.fecha_nacimiento,
                    },
                    modalActualizar: true
                });
            }

        }).catch(error => {
            console.log(error.message);
        })
    }

    handleChange = async e => {
        e.persist();
        await this.setState({
            form: {
                ...this.state.form,
                [e.target.name]: e.target.value
            }
        });
        console.log(this.state.form);
    }

    componentDidMount() {
        this.peticionGet();
        const { match } = this.props;
        this.idLector = match.params.id;
    }


    peticionPutSolicitudLibro = (idLibro) => {
        axios.put("http://localhost:8585/solicitar-libro/" + Number(idLibro) + "/lector/" + this.idLector).then(response => {
            this.peticionGet();
            alert(response.data);
        })
    }

    peticionPut = () => {
        axios.put("http://localhost:8585/actulizar-datos/" + this.state.form.id, this.state.form).then(response => {
        })
    }

    peticionDelete = () => {
        axios.delete("http://localhost:8585/baja-lector/" + this.idLector).then(response => {
            this.setState({ modalEliminar: false });
            this.peticionGet();
        })
    }
    render() {
        const { form } = this.state;
        return (
            <div>
                <Container>
                    <Link to={`/historial-prestado/` + this.idLector}><Button color="success" >consultar libros prestados</Button></Link>
                    {"   "}
                    <Button color="success" onClick={() => { this.peticionGetLector(); }}  >Actualizar</Button>
                    {"   "}
                    <Button className="btn btn-danger" onClick={() => { this.setState({ modalEliminar: true }); }}  >dar de baja cuenta</Button>
                    <Table  >
                        <thead>
                            <tr>
                                <th>Autor</th>
                                <th>Titulo</th>
                                <th>Fecha Publicacion</th>
                                <th>Genero</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.data.map(libro => {
                                return (
                                    <tr>
                                        <td>{libro.autor}</td>
                                        <td>{libro.titulo}</td>
                                        <td>{libro.fecha_publicacion}</td>
                                        <td>{libro.genero.nombre_genero}</td>
                                        <td>
                                            <Button color="primary" onClick={() => { this.peticionPutSolicitudLibro(libro.id) }}>solicitar</Button>
                                            {"   "}
                                        </td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </Container>
                <Modal isOpen={this.state.modalActualizar}>
                    <ModalHeader style={{ display: 'block' }}>
                        <span onClick={() => this.modalInsertar()}>x</span>
                    </ModalHeader>
                    <ModalBody>
                        <div className="form-group">
                            <label htmlFor="nombre">Nombre</label>
                            <input className="form-control" type="text" name="nombre" id="nombre" onChange={this.handleChange} value={form.nombre} />
                            <br />
                            <label htmlFor="correo">Correo</label>
                            <input className="form-control" type="text" name="correo" id="correo" onChange={this.handleChange} value={form.correo} />
                            <br />
                            <label htmlFor="fecha_nacimiento">Fecha Nacimiento</label>
                            <input className="form-control" type="text" name="fecha_nacimiento" id="fecha_nacimiento" onChange={this.handleChange} value={form.fecha_nacimiento} />
                        </div>
                    </ModalBody>

                    <ModalFooter>
                        <Button color="success" onClick={() => { this.peticionPut(); this.setState({ modalActualizar: false }) }}>Actualizar</Button>
                        <Button className="btn btn-danger" onClick={() => this.setState({ modalActualizar: false })}>Cancelar</Button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={this.state.modalEliminar}>
                    <ModalBody>
                        Estás seguro que deseas dar debaja tu cuenta {form && form.nombre}
                    </ModalBody>
                    <ModalFooter>
                        <Button className="btn btn-danger" onClick={() => this.peticionDelete()}>Sí</Button>
                        <Button className="btn btn-secundary" onClick={() => this.setState({ modalActualizar: false })}>No</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

export default SolicitarLibro;