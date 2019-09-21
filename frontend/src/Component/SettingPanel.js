import React from "react";
// import ReactDOM from "react-dom";
import { Button,InputNumber} from "antd";


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
        // this.handleChange = this.handleChange.bind(this);
        // this.toggle = this.toggle.bind(this);
    }

    // handleChange = (event) =>{
    //   console.log("change number\n");
    //   this.setState({numberAnt : event.target.value},
    //     ()=>{ console.log(this.state.numberAnt)
    //   });
    //   console.log(this.state.numberAnt);
    // }
    changeNumber = option => {
        let  velocity  = this.state.antVelocity
        let position = this.state.position
        if (option === "+") {
            // this.setState({ numberAnt: this.state.numberAnt + 1 });
            velocity.push(10);
            position.push(0)
        } else if (option === "-") {
            this.state.antVelocity.pop();
            this.state.position.pop();
        }
        this.setState({numberAnt:this.state.antVelocity.length})
        console.log(this.state.antVelocity)
        this.forceUpdate();
    };

    adjustInput = (ant,index) => {
        return(
            <div key={index.toString()} style={{ display: 'flex', alignItems: 'center', marginTop: 10 }}>
                <b>Ant {index + 1}'s velocity': </b>
                <span style={{ marginLeft: 10, marginRight: 10 }}></span>
                <InputNumber size="small" max={9}
                             min={1} onChange={this.setVelocity(index)} />
            </div>
        );

    };
    setVelocity = (index) => (value) => {
        this.setState({antVelocity: this.state.antVelocity.map((v,i)=>{
                if(i===index)
                {
                    return value;
                }
                return v;
            })})
        console.log("velocity: "+ this.state.antVelocity);
    }
    adjustPosition = (ant,index) => {
        return(
            <div key={index.toString()} style={{ display: 'flex', alignItems: 'center', marginTop: 10 }}>
                <b>Ant {index + 1}'s position:</b>
                <span style={{ marginLeft: 8, marginRight: 10 }}></span>
                <InputNumber size = "small" max={this.state.stickLength} min={0}
                             onChange = {this.setPosition(index)}/>
            </div>
        )
    }
    setPosition = (index)=> (value) =>{
        this.setState({
            position: this.state.position.map((v,i)=>
            {
                if(i===index){
                    return value;
                }
                return v;
            })
        })
        console.log("position: "+this.state.position);
    }
    changeGameStatus = () => {
        this.setState({gameStatus: true})
    }
    resetGame =() => {
        for(let i = this.state.numberAnt;i >1; i--){
            this.state.antVelocity.pop();
            this.state.position.pop();
        }
        this.setState({
            numberAnt:1,
            antVelocity:[10],
            position:[0]
        })

    }
    setStickLength = (value) => {
        this.setState(
            {stickLength: value}
        );
        console.log("sticklength: "+value);
    }
    render() {
        let number = this.state.numberAnt;
        let velocity = this.state.antVelocity;
        let position = this.state.position;
        return (
            <div style={styles.container}>
                <div style = {styles.settingsContainer}>
                    <b>basic setting:</b>
                    <span style={styles.label} >stick length:</span>
                    <InputNumber onChange = {this.setStickLength} />
                    <span> ant number: </span>

                    <div style={{
                        display: 'flex',
                        alignItems: 'center',
                        marginTop: 20,
                        marginBottom: 20
                    }} >


                        <Button icon="plus" onClick={()=>this.changeNumber("+")} style={styles.buttonContainer}>
                            plus
                        </Button>
                        <Button icon="minus" onClick={()=>this.changeNumber("-")} style={styles.buttonContainer}>
                            minus
                        </Button>
                    </div>
                    {velocity.map(this.adjustInput)}
                    {position.map(this.adjustPosition)}
                    <Button icon="start" onClick={()=>this.changeGameStatus} disabled = {this.state.gameStatus} >
                        start
                    </Button>
                    <Button icon="reset" onClick={this.resetGame} disabled = {this.state.gameStatus} >
                        reset
                    </Button>

                </div>
            </div>
        );
    }

    toggle(index) {
        this.setState({ children: this.state.children.map((v, i) => i !== index) });
    }
}

// class MyComponent extends React.Component {
//   render() {
//     c text = this.props.visible ? "visible" : "hidden";
//    return <div onClick={this.props.toggle}>{text}</div>;
//   }
// }

const styles = {
    container: {
        padding: 20,
        paddingBottom: 50,
        paddingTop: 20,
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'flex-end',
        width: 250,

    },
    settingsContainer: {
        flex: 1,
        display: "flex",
        flexDirection: "column",
        paddingTop: 10
    },
    buttonContainer: {
        marginTop: 16,
        flex: 2
    },
};

//ReactDOM.render(<SettingPanel />, document.getElementById("root"));
