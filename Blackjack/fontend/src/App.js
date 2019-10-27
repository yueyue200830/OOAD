import React from 'react';
import './App.css';
import { Modal, Button, InputNumber, Divider } from 'antd';
import axios from 'axios';

class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            GameInfo: true,
            GameSetting: false,
            GameStart: false,
            playerNumber: 3,
            playerHandNumber: [1, 1, 1],
            turn: 1,
            playerStatus: [true],
            initialDraw: true,
            cardInfo: [[[2, 16, 48]], [[11, 14, 50]]],
            currentPlayer: 1,
            currentHand: 1,
            // betList: [[10,12],[20]],
            betList: [{player: 1, hand: 1, bet: 10}, {player: 2, hand: 1, bet: 10}, {player: 3, hand: 1, bet: 10}],
        };
    }

    startGame = () => {
        axios.post("http://127.0.0.1:8080/startGame", this.state
        ).then(
            res => {
                console.log("Start Game!");
                console.log(res.data.info[0]);
                this.setState({
                    GameSetting: false,
                    GameStart: true,
                    cardInfo: res.data.info[0],
                });
            }
        );
    };

    handleGameInfoOk = () => {
        this.setState({
            GameInfo: false,
        });
        if (!this.state.GameStart) {
            this.setState({
                GameSetting: true
            });
        }
    };

    setPlayerNumber = (num) => {
        if (num < this.state.playerNumber) {
            let newBetList = [];
            for (let i = 0; i < this.state.betList.length; i++) {
                if (this.state.betList[i].player <= num) {
                    newBetList.push(this.state.betList[i])
                }
            }

            let newPlayerHand = [];
            for (let i = 0; i < num; i++) {
                newPlayerHand.push(this.state.playerHandNumber[i])
            }

            this.setState({
                betList: newBetList,
                playerNumber: num,
                playerHandNumber: newPlayerHand
            });
        } else if (num > this.state.playerNumber) {
            let newPlayerHand = this.state.playerHandNumber;

            for (let i = this.state.playerNumber + 1; i <= num; i++) {
                this.state.betList.push({player: i, hand: 1, bet: 10})
                newPlayerHand.push(1);
            }

            this.setState({
                playerNumber: num,
                playerHandNumber: newPlayerHand
            });
        }

    };

    addHand = (index) => {
        let newBetList = [];
        let player = this.state.betList[index].player;
        let hand = 1;
        let hasAdded = false;
        for (let i = 0; i < this.state.betList.length; i++) {
            if (this.state.betList[i].player <= player) {
                hand = this.state.betList[i].hand;
            } else {
                if (this.state.betList[i - 1].player === player) {
                    newBetList.push({player: player, hand: hand + 1, bet: 10});
                    hasAdded = true;
                }
            }
            newBetList.push(this.state.betList[i]);
        }
        if (!hasAdded) {
            newBetList.push({player: player, hand: hand + 1, bet: 10});
        }

        this.setState({
            betList: newBetList,
            playerHandNumber: this.state.playerHandNumber.map((num, i) => {
                if (i === player - 1) {
                    return num + 1;
                } else {
                    return num;
                }
            })
        });
    };

    deleteHand = (index) => {
        let player = this.state.betList[index].player;
        if (index + 1 < this.state.betList.length && this.state.betList[index].player === this.state.betList[index + 1].player) {
            this.state.betList.splice(index, 1);
            for (let i = index; i < this.state.betList.length; i++) {
                if (this.state.betList[i].player === player) {
                    this.state.betList[i].hand -= 1;
                } else {
                    break;
                }
            }
        } else if (this.state.betList[index].hand !== 1) {
            this.state.betList.splice(index, 1);
        } else {
            return;
        }

        this.setState({
            playerHandNumber: this.state.playerHandNumber.map((num, i) => {
                if (i === player - 1) {
                    return num - 1;
                } else {
                    return num;
                }
            })
        });
        this.forceUpdate();
    };

    changeBet = (index, value) => {
        this.setState({
            betList: this.state.betList.map((bet, i) => {
                if (index === i) {
                    bet.bet = value;
                }
                return bet;
            })
        });
    };

    adjustBet = (bet, index) => {
        return (
            <tr key={index} className="Game-setting-table-tr">
                <td>Player {bet.player}</td>
                <td>Hand {bet.hand}</td>
                <td>
                    Bet:
                    <InputNumber
                        size="small"
                        min={1}
                        max={100000}
                        onChange={(value) => this.changeBet(index, value)}
                        name={index}
                        defaultValue={bet.bet} />
                </td>
                <td>
                    <Button
                        icon="plus"
                        shape="circle"
                        size="small"
                        onClick={() => {this.addHand(index)}}
                        className="Game-setting-table-button"/>
                    <Button
                        icon="minus"
                        shape="circle"
                        size="small"
                        onClick={() => {this.deleteHand(index)}}
                        className="Game-setting-table-button"/>
                </td>
            </tr>
        )
    };

    showRule = () => {
        this.setState({
            GameInfo: true,
        });
    };

    drawCard = () => {
        console.log("start draw");
        axios.get("http://127.0.0.1:8080/drawCard",{
            params:{playerNo: 1, handNo: 1}
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
    };

    revertCard = (cardNumber) => {
        console.log("card number " + cardNumber);
        let color = Math.floor((cardNumber + 1) / 13);
        let value = cardNumber % 13;
        if (value === 0) {
            value = 13;
        }
        let description = "";
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
            default:
                console.log("color = 3");
                description += "Heart " + value;
        }
        description += ".jpg";
        //console.log("transfer over " + description);
        return description;
    };

    displayCard = (card, index) => {
        let imgSrc = this.revertCard(card);
        console.log("load " + imgSrc);
        return(
            <div key={index} className="Each-Card" >
                <img width="15%" src={require("./Card/" + imgSrc)} alt={this.revertCard(index)} />
            </div>
        )
    };

    doubleBet = () => {
        let betList = this.state.betList;
        console.log("double bet");
        this.setState({
           betList: this.state.betList[0][0]*2}
        );
        this.forceUpdate();
        axios.get("http://127.0.0.1:8080/doubleBet",{
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
    };

    nextDraw = () => {
        console.log("next player");
        let currentPlayer = this.state.currentPlayer;
        if (this.state.currentPlayer <= this.state.playerNumber) {
            this.setState({
                    currentPlayer: this.state.currentPlayer + 1
                });
        }
        this.forceUpdate();
        console.log(this.state.currentPlayer);
    };

    render() {
        return (
            <div className="App">
                <div className="Player-part">
                    <Button className="rule" type="primary" onClick={this.showRule} shape="round">Rule</Button>
                    <div className="Player-Info">
                        Player 1's Hand 1's Turn:
                        <br/>
                        Current bet is: {this.state.betList[0][0]}
                    </div>
                    <div className="All-Card">
                        {this.state.cardInfo[this.state.currentPlayer - 1][0].map(this.displayCard)}
                    </div>
                    <div className="Choice-Part">
                        <div className="Hint-Message">
                            Choose the operation you want to do:
                        </div>
                        <div className="Choice-Buttons">
                            <div className="Each-Button">
                                <Button type="primary" onClick={this.doubleBet} size="large">Double</Button>
                            </div>
                            <div className="Each-Button">
                                <Button type="primary" onClick={this.drawCard} size="large">Draw</Button>
                            </div>
                            <div className="Each-Button">
                                <Button type="primary" onClick={this.nextDraw} size="large">Pass</Button>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="Information-part">
                    <div className="Each-Opponent">
                        <div className="Opponent-Info">
                            Player 2's Hand 2:
                        </div>
                        <div className="Total-Card">
                            <img width="15%" className="Every-Card" src={require("./Card/Clover 1.jpg")} alt="Clover 1.jpg"/>
                            <img width="15%" className="Every-Card" src={require("./Card/Clover 1.jpg")} alt="Clover 1.jpg"/>
                            <img width="15%" className="Every-Card" src={require("./Card/Clover 1.jpg")} alt="Clover 1.jpg"/>
                            <img width="15%" className="Every-Card" src={require("./Card/Clover 1.jpg")} alt="Clover 1.jpg"/>
                        </div>
                    </div>
                    <Divider className="divider" />
                </div>
            </div>
        );
    }
}

export default App;
