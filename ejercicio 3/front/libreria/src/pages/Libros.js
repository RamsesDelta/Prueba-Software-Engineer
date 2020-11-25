import React from 'react';
import axios from "axios";

import { Table, Button, Container, Modal, ModalHeader, ModalBody, ModalFooter, } from "reactstrap";

const url = "http://localhost:8282/listar";

class Libros extends React.Component {

    state = {
        data: [],
        modalInsertar: false,
        modalEliminar: false,
        form: {
            id: '',
            autor: '',
            titulo: '',
            fecha_publicacion: '',
            cantidad: '',
            genero: ''
        },
        dataGeneros: []
    }

    peticionGet = () => {
        axios.get(url).then(response => {
            this.setState({ data: response.data });
        }).catch(error => {
            console.log(error.message);
        })
    }

    peticionGetGeneros = () => {
        axios.get("http://localhost:8282/obtener-generos").then(response => {
            this.setState({ dataGeneros: response.data });
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

    seleccionarLibro = (libro) => {
        this.setState({
            tipoModal: 'actualizar',
            form: {
                id: libro.id,
                nombre: libro.nombre,
                autor: libro.autor,
                titulo: libro.titulo,
                fecha_publicacion: libro.fecha_publicacion,
                cantidad: libro.cantidad,
                genero: libro.genero.id

            }
        })
    }

    modalInsertar = () => {
        this.setState({ modalInsertar: !this.state.modalInsertar });
    }

    peticionPost = async () => {
        delete this.state.form.id;
        await axios.post("http://localhost:8282/agregar", this.state.form).then(response => {
            this.modalInsertar();
            this.peticionGet();
        }).catch(error => {
            console.log(error.message);
        })
    }


    peticionPut = () => {
        axios.put("http://localhost:8282/actulizar/" + this.state.form.id, this.state.form).then(response => {
            this.modalInsertar();
            this.peticionGet();
        })
    }

    peticionDelete = () => {
        axios.delete("http://localhost:8282/eliminar/" + this.state.form.id).then(response => {
            this.setState({ modalEliminar: false });
            this.peticionGet();
        })
    }

    componentDidMount() {
        this.peticionGet();
        this.peticionGetGeneros();
    }


    render() {
        const { form } = this.state;
        return (
            <div>
                <Container>
                    <Button color="success" onClick={() => { this.setState({ form: null, tipoModal: 'insertar' }); this.modalInsertar() }} >Agregar</Button>
                    <Table  >
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Autor</th>
                                <th>Titulo</th>
                                <th>Fecha Publicacion</th>
                                <th>Catidad</th>
                                <th>Genero</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.data.map(libro => {
                                return (
                                    <tr>
                                        <td>{libro.id}</td>
                                        <td>{libro.autor}</td>
                                        <td>{libro.titulo}</td>
                                        <td>{libro.fecha_publicacion}</td>
                                        <td>{libro.cantidad}</td>
                                        <td>{libro.genero.nombre_genero}</td>
                                        <td>
                                            <Button color="primary" onClick={() => { this.seleccionarLibro(libro); this.modalInsertar() }}>editar</Button>
                                            {"   "}
                                            <Button color="danger" disabled={libro.cantidad === 0 ? true : false} onClick={() => { this.seleccionarLibro(libro); this.setState({ modalEliminar: true }) }} > eliminar</Button>
                                        </td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </Container>

                <Modal isOpen={this.state.modalInsertar}>
                    <ModalHeader style={{ display: 'block' }}>
                        <span onClick={() => this.modalInsertar()}>x</span>
                    </ModalHeader>
                    <ModalBody>
                        <div className="form-group">
                            <label htmlFor="id">ID</label>
                            <input className="form-control" type="text" name="id" id="id" readOnly onChange={this.handleChange} value={form ? form.id : '0'} />
                            <br />
                            <label htmlFor="autor">Autor</label>
                            <input className="form-control" type="text" name="autor" id="autor" onChange={this.handleChange} value={form ? form.autor : ''} />
                            <br />
                            <label htmlFor="titulo">Titulo</label>
                            <input className="form-control" type="text" name="titulo" id="titulo" onChange={this.handleChange} value={form ? form.titulo : ''} />
                            <br />
                            <label htmlFor="fecha_publicacion">Fecha Publicacion</label>
                            <input className="form-control" type="text" name="fecha_publicacion" id="fecha_publicacion" onChange={this.handleChange} value={form ? form.fecha_publicacion : ''} />
                            <br />
                            <label htmlFor="cantidad">Cantidad</label>
                            <input className="form-control" type="text" name="cantidad" id="cantidad" onChange={this.handleChange} value={form ? form.cantidad : ''} />
                            <br />
                            <label htmlFor="genero_id">Genero</label>

                            <select onChange={this.handleChange} name="genero" value={form ? form.genero : this.state.dataGeneros.id}>
                                {this.state.dataGeneros.map(genero => {
                                    return (<option value={genero.id} > {genero.nombre_genero} </option>)
                                })}
                            </select>
                            <br />
                        </div>
                    </ModalBody>

                    <ModalFooter>
                        {this.state.tipoModal === 'insertar' ?
                            <Button className="btn btn-success" onClick={() => this.peticionPost()}>
                                Insertar
                  </Button> : <Button className="btn btn-primary" onClick={() => this.peticionPut()}>
                                Actualizar
                  </Button>
                        }
                        <Button className="btn btn-danger" onClick={() => this.modalInsertar()}>Cancelar</Button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={this.state.modalEliminar}>
                    <ModalBody>
                        Estás seguro que deseas eliminar el libro {form && form.nombre}
                    </ModalBody>
                    <ModalFooter>
                        <Button className="btn btn-danger" onClick={() => this.peticionDelete()}>Sí</Button>
                        <Button className="btn btn-secundary" onClick={() => this.setState({ modalEliminar: false })}>No</Button>
                    </ModalFooter>
                </Modal>
            </div >
        );
    }
}
export default Libros;