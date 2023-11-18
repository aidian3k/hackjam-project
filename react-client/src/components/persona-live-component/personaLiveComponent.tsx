import { Box, Paper, Typography } from "@mui/material";
import Image from "next/image";
import axios from "axios";

export default function PersonaLiveComponent(props: any) {

        return (
        <Paper elevation={2}  sx={{ p: 2, height: '100%' }}>
            <Box style={{width: '100%', height: '100%', position: 'relative' }}>
                {props.isPlaying ?
                    <Image
                        alt={"epic dolphin"}
                        src={"/dolphin.gif"}
                        layout='fill'
                        objectFit='contain'
                        style={{ borderRadius: 5 }}
                    /> : <Image src={'/img/dolphin_doctor.jpg'} alt={'doctor'} layout={'fill'} objectFit={'contain'} style={{borderRadius: 5}}/>}
            </Box>

        </Paper>
    );
}
