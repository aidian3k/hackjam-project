import { Box, Paper, Typography } from "@mui/material";
import Image from "next/image";

export default function PersonaLiveComponent() {
    return (
        <Paper elevation={2}  sx={{ p: 2, height: '100%' }}>
            <Box style={{width: '100%', height: '100%', position: 'relative' }}>
                    <Image
                        alt={"product"}
                        src={"/img/dolphin_doctor.jpg"}
                        layout='fill'
                        objectFit='contain'
                        style={{ borderRadius: 5 }}
                    />
            </Box>

        </Paper>
    );
}
