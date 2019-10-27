import React from 'react';
import './App.css';
import axios from 'axios';
import { Modal, Button, InputNumber, Divider } from 'antd';

class App extends React.Component {
    state = {
        GameInfo: true,
        GameSetting: false,
        playerNumber: 1,
        playerHandNumber: [1],
        betList: [{player: 1, hand: 1, bet: 10}],
    };

    showGameInfo = () => {
        this.setState({
            GameInfo: true,
        });
    };

    handleGameInfoOk = () => {
        this.setState({
            GameInfo: false,
            GameSetting: true
        });
    };

    startGame = () => {
        axios.post("http://127.0.0.1:8080/startGame", this.state
        ).then(
            res => {
                console.log("Start Game!");
                this.setState({
                    GameSetting: false
                });
            }
        );
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

    render() {
        return (
            <div className="App">
                <div className="Player-part">
                    Player Page
                </div>
                <div className="Information-part">
                    other information
                </div>
                <div>
                    <Modal
                        title="Game Setting"
                        visible={this.state.GameSetting}
                        closable={false}
                        footer={null}
                        width="500px"
                        centered={true}
                    >
                        <div className="Game-setting-number">
                            <p className="Game-setting-number-text">Player Number:</p>
                            <InputNumber
                                min={1}
                                max={5}
                                defaultValue={1}
                                size="small"
                                className="Game-setting-number-input"
                                onChange = {this.setPlayerNumber} />
                        </div>
                        <Divider />
                        <table className="Game-setting-table">
                            <tbody>
                                {this.state.betList.map(this.adjustBet)}
                            </tbody>
                        </table>
                        <div className="Game-info-ok-div">
                            <Button
                                type="primary"
                                onClick={this.startGame}
                                className="Game-info-ok"
                            >Start Game</Button>
                        </div>
                    </Modal>
                    <Modal
                        title="Game Rule"
                        visible={this.state.GameInfo}
                        closable={false}
                        footer={null}
                        width="700px"
                        centered={true}
                    >
                        <p>在一局游戏中，有一或多位玩家与庄家进行游戏，其中庄家为机器人，而每个玩家又可以拥有多副手牌和庄家进行比较。
                            每位玩家都需要为其每副手牌进行投注，若最后获胜，可以获得额外的同金额的奖金，若失败，则不归还此投注金额。
                            为确保公平性，每局都采用一副牌（52张牌，除去大小王）来比赛，并且设定总参与手牌不超过9个。</p>
                        <p>在比赛过程中，首先对每位玩家的每个手牌发两张牌，之后对玩家逐一询问是否需要为当前手牌添加牌或者加倍投注，
                            当一位玩家不再需要或已有五张手牌时，会跳至下一个手牌或下一位玩家，直至所有玩家均已结束询问。
                            此时，庄家将会进行抽牌。最后会进行游戏总结并判断输赢。</p>
                        <p>在游戏期间，每位玩家的第一张牌均为暗牌，对其他人不可见，直到游戏结束开牌。
                            对于牌面为2-10的牌，它们代表着自己本身的值，对于J、Q、K将它们均记为10，
                            而对于Ace，它可以被记为1或11，最终算输赢时会按照最大且不超多21的值作为这个手牌的值。</p>
                        <p>当游戏结束时，每位玩家的每个手牌与庄家进行比较，若双方有一方和超过21，则另一方获胜，
                            若双方均超过21，则庄家获胜，若双方均没超过21，那么比较和的大小，较大的一方获胜，
                            又当双方和相同时，比较牌的数量，牌数较少的一方获胜，而对于和与牌数均相同的情况，认定为玩家获胜。</p>
                        <div className="Game-info-ok-div">
                            <Button
                                type="primary"
                                onClick={this.handleGameInfoOk}
                                className="Game-info-ok"
                            >ok</Button>
                      </div>
                    </Modal>
                </div>
            </div>
        );
    }
}

export default App;
