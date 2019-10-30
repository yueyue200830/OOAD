import React from 'react';
import './App.css';
import { Modal, Button, InputNumber, Divider, message, Card } from 'antd';
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
            cardInfo: [],
            currentPlayer: 1,
            currentHand: 1,
            bet: [],
            betList: [{player: 1, hand: 1, bet: 10}, {player: 2, hand: 1, bet: 10}, {player: 3, hand: 1, bet: 10}],
            dealerLose: false,
            winnerList: [[]],
            disableButton: false,
        };
    }

    /**
     * Start game by sending setting data to the backend and get the cards information.
     */
    startGame = () => {
        this.convertBetList();
        axios.post("http://127.0.0.1:8080/startGame", this.state
        ).then(
            res => {
                console.log("Start Game!");
                console.log(res.data.info);
                this.setState({
                    GameSetting: false,
                    GameStart: true,
                    cardInfo: res.data.info[0],
                });
            }
        );
    };

    /**
     * Convert the bet list to another list.
     */
    convertBetList = () => {
        let bet = [];
        for (let i = 0; i < this.state.betList.length; i++) {
            let b = this.state.betList[i];
            let p = b.player - 1;
            if (bet.length > p) {
                bet[p].push(b.bet);
            } else {
                bet.push([b.bet]);
            }
        }
        this.setState({
            bet: bet,
        });
    };

    /**
     * Click handler of the ok button in the rule.
     */
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

    /**
     * Set player number and change the input panel correspondingly.
     * @param num The input player number
     */
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
                this.state.betList.push({player: i, hand: 1, bet: 10});
                newPlayerHand.push(1);
            }

            this.setState({
                playerNumber: num,
                playerHandNumber: newPlayerHand
            });
        }
    };

    /**
     * Add a hand in the input button.
     * @param index The player number.
     */
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

    /**
     * Delete a hand of the player.
     * @param index The player number.
     */
    deleteHand = (index) => {
        let player = this.state.betList[index].player;
        if (index + 1 < this.state.betList.length && this.state.betList[index].player === this.state.betList[index + 1].player) {
            this.state.betList.splice(index, 1);
            this.setState({
                betList: this.state.betList.map((b) => {
                    if (b.player === player) {
                        b.hand -= 1;
                    }
                    return b;
                })
            });
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

    /**
     * Function is called when the bet is changed.
     * @param index The index of the bet to be changed.
     * @param value The changed value.
     */
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

    /**
     * The html for setting part.
     * @param bet One of betList.
     * @param index The index.
     * @returns {html} One setting line.
     */
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

    /**
     * Click button to show the rule.
     */
    showRule = () => {
        this.setState({
            GameInfo: true,
        });
    };

    /**
     * Draw a card for a player's hand.
     */
    drawCard = () => {
        console.log("start draw");
        axios.get('http://localhost:8080/drawCard',{
            params:{playerNo: this.state.currentPlayer - 1, handNo: this.state.currentHand - 1}
        })
            .then(
                res => {
                    console.log(res.data);
                    this.state.cardInfo[this.state.currentPlayer - 1][this.state.currentHand - 1].push(res.data.newCard[0]);
                    this.forceUpdate();
                    if (res.data.lose[0] === true) {
                        this.setState({
                            disableButton: true
                        });
                        this.onePlayerLostMessage();
                        setTimeout(() => {
                            this.nextDraw();
                            this.setState({
                                disableButton: false
                            });
                        }, 3000);
                    }
                }
                )
            .catch(
                res => {
                    console.log("error");
                }
            )
    };

    /**
     * Revert card number to the image name.
     * @param cardNumber The card number.
     * @returns {string} The image name.
     */
    revertCard = (cardNumber) => {
        let color = Math.floor((cardNumber + 1) / 13);
        let value = cardNumber % 13;
        if (value === 0) {
            value = 13;
        }
        let description = "";

        switch (color) {
            case 0:
                description += "Clover " + value;
                break;
            case 1:
                description += "Spade " + value;
                break;
            case 2:
                description += "diamond " + value;
                break;
            default:
                description += "Heart " + value;
        }
        description += ".jpg";
        return description;
    };

    /**
     * Display players' cards in the right panel.
     * @param playerCard A list of a player's cards.
     * @param player The player's number.
     * @returns {html} All hands html of a player.
     */
    displayHintCard = (playerCard, player) => {
        if (this.state.GameStart || this.state.currentPlayer > this.state.playerNumber) {
            return(
                playerCard.map((handCard, hand) => {return this.displayHand(handCard, player, hand)})
            )
        }
    };

    /**
     * Display a hand of a player.
     * @param handCard All cards of a hand.
     * @param player The player's number.
     * @param hand The hand's number.
     * @returns {html} An html to show a hand's cards.
     */
    displayHand = (handCard, player, hand) => {
        if (player + 1 === this.state.currentPlayer && hand + 1 === this.state.currentHand) {
            return(
                <Card key={hand}
                      title={this.displayPlayerHintText(player, hand)}
                      className="Hint-Card"
                      style={{backgroundColor: "#92511f"}}>
                    {handCard.map(this.displayCard)}
                </Card>
            )
        } else {
            return (
                <Card key={hand} title={this.displayPlayerHintText(player, hand)} className="Hint-Card">
                    {handCard.map(this.displayCard)}
                </Card>
            )
        }
    };

    /**
     * Display player's hint text.
     * @param player The player number.
     * @param hand The hand number.
     * @returns {string} hint text.
     */
    displayPlayerHintText = (player, hand) => {
        if (player < this.state.playerNumber) {
            return "Player " + (player + 1) + "'s Hand " + (hand + 1);
        } else {
            return "Dealer";
        }
    };

    /**
     * Display a cards.
     * @param card The card number.
     * @param index The index of the card.
     * @returns {html} an html for a image card.
     */
    displayCard = (card, index) => {
        let imgSrc = this.revertCard(card);
        return(
            <img width="16%" key={index} className="Every-Card" src={require("./Card/" + imgSrc)} alt={imgSrc}/>
        )
    };

    /**
     * Double the bet.
     */
    doubleBet = () => {
        console.log("double bet");
        this.setState({
           bet: this.state.bet.map((b, i) => {
               if (i === this.state.currentPlayer - 1) {
                   b[this.state.currentHand - 1] *= 2;
               }
               return b;
           })
        });
        this.forceUpdate();

        axios.get("http://localhost:8080/doubleBet",{
            params:{playerNo: this.state.currentPlayer - 1, handNo: this.state.currentHand - 1}
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

    /**
     * Turn to the next player / hand.
     */
    nextDraw = () => {
        console.log("next player");
        if (this.state.currentHand < this.state.playerHandNumber[this.state.currentPlayer - 1]) {
            this.setState({
                currentHand: this.state.currentHand + 1
            });
        } else {
            if (this.state.currentPlayer === this.state.playerNumber) {
                this.setState(
                    {
                        currentPlayer: this.state.currentPlayer + 1,
                        currentHand: 1
                    }
                );
                console.log("Now it is dealer's turn");
                axios.get("http://localhost:8080/dealerTurn")
                    .then(
                        res => {
                            console.log(res.data);
                            let loseCondition = res.data.dealerLose[0];
                            let dataCondition = res.data.finalInfo[0];
                            console.log(loseCondition);
                            console.log(dataCondition);
                            this.setState({
                                cardInfo: dataCondition,
                                dealerLose: loseCondition,
                            });
                            console.log("set timeout ");
                            setTimeout(() => {this.requestWinner()},5000);
                        }
                    )
                    .catch(
                        res => {
                            console.log("fail to complete dealer's turn");
                            console.log(res);
                        }
                    )
            } else {
                this.setState({
                    currentPlayer: this.state.currentPlayer + 1,
                    currentHand: 1
                });
            }
        }
        console.log(this.state.currentPlayer);
    };

    /**
     * Get the winner.
     */
    requestWinner = () => {
        axios.get("http://localhost:8080/getWinner")
            .then(
                res => {
                    console.log("get result " + res.data.winnerList);
                    console.log(res.data.winnerList[0]);
                    this.setState({
                        winnerList: res.data.winnerList[0],
                        GameStart: false,
                        currentPlayer: this.state.currentPlayer + 1
                    });
                    console.log(this.state.GameStart);
                }
            )
            .catch(
                res => {
                    console.log("fail to get winners");
                }
            )
    };

    /**
     * Show lost hint if the sum is larger than 21.
     */
    onePlayerLostMessage = () => {
        message.info("Oops! You are over 21!");
    };

    /**
     * Show text for current player.
     * @returns {html} Return html of the player.
     */
    showBet = () => {
        if (this.state.currentPlayer <= this.state.playerNumber) {
            return(
                <div className="Player-Info">
                    <div>
                        Player {this.state.currentPlayer}'s Hand {this.state.currentHand}'s Turn:
                    </div>
                    <div>
                        Current bet is: {this.state.bet[this.state.currentPlayer - 1][this.state.currentHand - 1]}
                    </div>
                </div>
            )
        } else {
            return(
                <div className="Player-Info">
                    <div>
                        Dealer's Turn:
                    </div>
                </div>
            )
        }
    };

    /**
     * Show winners.
     * @returns {html} the html of all winning players.
     */
    showWinner = () => {
        return this.state.winnerList.map(this.showEveryWinner);
    };

    /**
     * Show all winning hands of a player..
     * @param winner The player number.
     * @param winnerIndex The index of winner.
     * @returns {*}
     */
    showEveryWinner = (winner, winnerIndex) => {
        if (winner.length !== 0) {
            return (
                winner.map((hand, handIndex) => {
                    return this.showWinHand(hand, handIndex, winnerIndex)
                })
            )
        }
    };

    /**
     * Show a winning hand.
     * @param hand The hand number.
     * @param handIndex The hand index.
     * @param winnerIndex The winner index.
     * @returns {html} Return the html of the winning hand.
     */
    showWinHand = (hand, handIndex, winnerIndex) => {
       return(
           <div className="Winner" key={winnerIndex}>
               Player {winnerIndex + 1}'s Hand {hand + 1} Wins! Earned {this.state.bet[winnerIndex][hand]} dollars!
           </div>
       )
    };

    /**
     * The ask html with pass, double bet and draw function.
     * @returns {html} Return the html for ask.
     */
    showAsk = () => {
        if (this.state.GameStart && this.state.currentPlayer <= this.state.playerNumber) {
            return(
                <div className="Choice-Part">
                    <div className="Hint-Message">
                        Choose the operation you want to do:
                    </div>
                    <div className="Choice-Buttons">
                        <div className="Each-Button">
                            <Button type="primary" onClick={this.doubleBet} size="large" disabled={this.state.disableButton}>Double</Button>
                        </div>
                        <div className="Each-Button">
                            <Button type="primary" onClick={this.drawCard} size="large" disabled={this.state.disableButton}>Draw</Button>
                        </div>
                        <div className="Each-Button">
                            <Button type="primary" onClick={this.nextDraw} size="large" disabled={this.state.disableButton}>Pass</Button>
                        </div>
                    </div>
                </div>
            )
        }
    };

    /**
     * The left of current player's panel.
     * @returns {html} Current player's panel.
     */
    showPlayerPart = () => {
        if (this.state.GameStart) {
            return (
                <div className="Player-round">
                    {this.showBet()}
                    <div className="All-Card">
                        {this.state.cardInfo[this.state.currentPlayer - 1][this.state.currentHand - 1].map(this.displayCard)}
                    </div>
                    {this.showAsk()}
                </div>
            )
        } else if (this.state.currentPlayer > this.state.playerNumber){
            console.log(this.state.winnerList);
            let dealerWin = true;
            for(let i = 0;i < this.state.winnerList.length; i++) {
                if (this.state.winnerList[i].length !== 0) {
                    dealerWin = false;
                    break;
                }
            }
            if (!dealerWin) {
                console.log("dealer not win");
                return (
                    <div className="Player-round">
                        {this.showWinner()}
                        <Button
                            type="primary"
                            size="large"
                            onClick={this.playAgain}
                            className="Game-info-ok"
                            style={{marginTop:"30px"}}
                        >Play Again</Button>
                    </div>
                )
            } else {
                return(
                    <div className="Player-round">
                        <div className="Dealer-Win">
                            Dealer Wins !
                        </div>
                        <Button
                            type="primary"
                            size="large"
                            onClick={this.playAgain}
                            className="Game-info-ok"
                            style={{marginTop:"30px"}}
                        >Play Again</Button>
                    </div>
                )
            }
        }
    };

    playAgain = () => {
        this.setState({
            GameSetting: true,
            GameStart: false,
            playerNumber: 3,
            playerHandNumber: [1, 1, 1],
            turn: 1,
            playerStatus: [true],
            initialDraw: true,
            cardInfo: [],
            currentPlayer: 1,
            currentHand: 1,
            betList: [{player: 1, hand: 1, bet: 10}, {player: 2, hand: 1, bet: 10}, {player: 3, hand: 1, bet: 10}],
            dealerLose: false,
            winnerList: [[]],
        });
    };

    render() {
        return (
            <div className="App">
                <div className="Player-part">
                    <Button className="rule" type="primary" onClick={this.showRule} shape="round">Rule</Button>
                    {this.showPlayerPart()}
                </div>
                <div className="Vertical-divider"/>
                <div className="Information-part">
                    {this.state.cardInfo.map(this.displayHintCard)}
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
                                defaultValue={3}
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
