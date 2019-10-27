import React from 'react';
import './App.css';
import { Button,Divider } from 'antd';
import axios from 'axios';
import 'antd/dist/antd.css';

class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            playerNumber: 3,
            playerHandNumber: [1,1],
            turn: 1,
            playerStatus: [true],
            initialDraw: true,
            cardInfo: [[[2,16,48]],[[11,14,50]]],
            currentPlayer: 1,
            currentHand: 1,
            betList:[[10,12],[20]],
        };
    }
    drawCard= () => {
        console.log("start draw");
        axios.get('/drawCard',{
            params:{playerNo:1,handNo: 1}
        })
            .then(
                res => {
                    console.log(res.data);
                    this.state.cardInfo[0][0].push(res.data);
                    this.forceUpdate();
                }
                )
            .catch(
                res => {
                    console.log("error");
                }
            )
    }
    revertCard= (cardNumber) => {
        console.log("card number " + cardNumber);
        let color = Math.floor((cardNumber + 1) / 13);
        let value = cardNumber % 13;
        let description = "";
        //console.log("color: " + color);
       // console.log("value: " + value);
        switch (color) {
            case 0:
                console.log("color = 0");
                description += "Clover " + value;
                break;
            case 1:
                console.log("color = 1");
                description += "Spade " + value;
                break;
            case 2:
                console.log("color = 2");
                description += "diamond " + value;
                break;
            case 3:
                console.log("color = 3");
                description += "Heart " + value;
                break;
        }
        description += ".jpg";
        //console.log("transfer over " + description);
        return description;
    }
    displayCard= (card,index) => {
        let imgSrc = this.revertCard(card);
        console.log("load " + imgSrc);
        return(
            <div className="Each-Card" >
                <img width="120px" height="200px" src={require("./Card/" + imgSrc)}>
                </img>
            </div>
        )
}
    doubleBet = ()=> {
        let betList = this.state.betList;
        console.log("double bet");
        this.setState({
           betList: this.state.betList[0][0]*2}
        )
        this.forceUpdate();
        axios.get("/doubleBet",{
            params:{playerNo:1,handNo:1}
        })
            .then(
                res => {
                    console.log("double success");
                }
            )
            .catch(
                res => {
                    console.log("double fail");
                }
            )
    }
    nextDraw = ()=> {
        console.log("next player");
        let currentPlayer = this.state.currentPlayer;
        if(this.state.currentPlayer <= this.state.playerNumber){
            this.setState(
                {
                    currentPlayer: this.state.currentPlayer + 1
                })
        }
        this.forceUpdate();
        console.log(this.state.currentPlayer);
    }
    render() {
        return (
            <div className="App">
                <div className="Player-part">
                    Player Page
                    <div className="Player-Info">
                        <Button className = "rule" type = "primary">Rule</Button>
                    </div>
                    <div className="Player-Info">
                        Player 1's Hand 1's Turn:
                        <br/>
                            Current bet is: {this.state.betList[0][0]}
                    </div>
                    <div className="All=Card">
                        {this.state.cardInfo[this.state.currentPlayer - 1][0].map(this.displayCard)}
                    </div>
                    <div className="Choice-Part">
                        <div className="Hint-Message">
                            Choose the operation you want to do:
                        </div>
                        <div className="Choice-Buttons">
                            <div className="Each-Button">
                            <Button type="primary" onClick={this.doubleBet}>Double</Button>
                            </div>
                            <div className="Each-Button">
                            <Button type="primary" onClick={this.drawCard}>Draw</Button>
                            </div>
                            <div className="Each-Button">
                            <Button type="primary" onClick={this.nextDraw}>Pass</Button>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="Information-part">
                    other information
                    <div className="Opponent-Info">
                        <div className="Each-Opponent">
                            <div className="Opponent-Info">
                                Player 2's Hand 2:
                            </div>
                            <div className="Total-Card">
                                <div className="Every-Card">
                                    <img width={70} height={110}class = "op-img" src={require("./Card/Clover 1.jpg")}>
                                    </img>
                                </div>
                                <div className="Every-Card">
                                    <img  width={70} height={110} src={require("./Card/Clover 1.jpg")}>
                                    </img>
                                </div>
                                <div className="Every-Card">
                                    <img width={70} height={110} src={require("./Card/Clover 1.jpg")}>
                                    </img>
                                </div>
                                <div className="Every-Card">
                                    <img width={70} height={110} src={require("./Card/Clover 1.jpg")}>
                                    </img>
                                </div>
                                <div className="Every-Card">
                                    <img width={70} height={110} src={require("./Card/Clover 1.jpg")}>
                                    </img>
                                </div>
                            </div>
                        </div>
                    </div>
                    <Divider className="divider"></Divider>
                </div>
            </div>
        );
    }
}

export default App;
