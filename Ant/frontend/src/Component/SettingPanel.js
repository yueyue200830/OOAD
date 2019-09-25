import React from "react";
import './SettingPanel.css';
import axios from 'axios';
import { Button,InputNumber } from "antd";

export default class SettingPanel extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            numberAnt: 1,
            antVelocity: [10],
            position: [0],
            stickLength: 100,
            gameStatus: false
        };
    }

    changeNumber = option => {
        let  velocity  = this.state.antVelocity;
        let position = this.state.position;
        if (option === "+") {
            velocity.push(10);
            position.push(0);
        } else if (option === "-") {
            this.state.antVelocity.pop();
            this.state.position.pop();
        }
        this.setState({numberAnt:this.state.antVelocity.length});
        console.log(this.state.antVelocity);
        this.forceUpdate();
    };

    adjustNumber = (ant, index) => {
        return(
          <b key={index} className="Setting-panel-ant-text Setting-panel-ant">
            Ant {index + 1}:
          </b>
        )
    };

    adjustInput = (ant, index) => {
        return(
            <div key={index.toString()} className="Setting-panel-ant">
                <InputNumber size="small" max={9}
                             min={1} onChange={this.setVelocity(index)} />
            </div>
        );

    };

    setVelocity = (index) => (value) => {
        this.setState({antVelocity: this.state.antVelocity.map((v,i)=>{
                if (i === index) {
                    return value;
                }
                return v;
            })})
        console.log("velocity: "+ this.state.antVelocity);
    };

    adjustPosition = (ant, index) => {
        return(
            <div key={index.toString()} className="Setting-panel-ant">
                <InputNumber size = "small" max={this.state.stickLength} min={0}
                             onChange = {this.setPosition(index)}/>
            </div>
        )
    };

    setPosition = (index) => (value) => {
        this.setState({
            position: this.state.position.map((v, i) => {
                if (i === index) {
                    return value;
                }
                return v;
            })
        });
        console.log("position: "+this.state.position);
    };

    changeGameStatus = () => {
        console.log("get!");
        this.setState({gameStatus: true});
        axios.post('http://127.0.0.1:8080' + "/test"
        ).then(
            res => {
                console.log("send axois finished.");
            }
        )
    };

    resetGame = () => {
        for (let i = this.state.numberAnt; i > 1; i--) {
            this.state.antVelocity.pop();
            this.state.position.pop();
        }
        this.setState({
            numberAnt:1,
            antVelocity:[10],
            position:[0]
        })
    };

    setStickLength = (value) => {
        this.setState(
            {stickLength: value}
        );
        console.log("sticklength: "+value);
    };

    render() {
        let number = this.state.numberAnt;
        let velocity = this.state.antVelocity;
        let position = this.state.position;
        return (
            <div className="Setting-panel-container">
              <b className="Setting-panel-title"> Basic Setting </b>
              <div className="Setting-panel-stick">
                <span className="Setting-panel-stick-text">
                  Stick length:
                </span>
                <InputNumber onChange = {this.setStickLength} />
              </div>
              <div className="Setting-panel-ant-number">
                <span className="Setting-panel-ant-number-text">
                  Ant number:
                </span>
                <div className="Setting-panel-ant-number-button-set">
                    <Button icon="plus" onClick={()=>this.changeNumber("+")}
                            className="Setting-panel-ant-number-button"/>
                    <Button icon="minus" onClick={()=>this.changeNumber("-")}
                            className="Setting-panel-ant-number-button"/>
                </div>
              </div>
              <div className="Setting-panel-input-frame">
                <div className="Setting-panel-input-col">
                  <b className="Setting-panel-ant-text"> Setting </b>
                  {velocity.map(this.adjustNumber)}
                </div>
                <div className="Setting-panel-input-col">
                  <b className="Setting-panel-ant-text">velocity</b>
                  {velocity.map(this.adjustInput)}
                </div>
                <div className="Setting-panel-input-col">
                  <b className="Setting-panel-ant-text">position</b>
                  {position.map(this.adjustPosition)}
                </div>
              </div>
              <div className="Setting-panel-button-set">
                <Button icon="start" className="Setting-panel-button"
                        onClick={this.changeGameStatus}
                        disabled = {this.state.gameStatus} >
                  start
                </Button>
                <Button icon="reset" className="Setting-panel-button"
                        onClick={this.resetGame}
                        disabled = {this.state.gameStatus} >
                  reset
                </Button>
              </div>
            </div>
        )
    }
}
