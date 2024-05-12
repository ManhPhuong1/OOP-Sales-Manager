import * as React from 'react';
import { useState, useEffect } from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Modal } from '@mui/material';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import sapoLogo from '../../images/sapo.png';
import OtpInput from 'react-otp-input';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import * as SignUpServices from '../../services/auth/signup';

import classNames from 'classnames/bind';
import styles from './Signup.module.scss';
import { useNavigate } from 'react-router-dom';

const cx = classNames.bind(styles);

const defaultTheme = createTheme();

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    borderRadius: 5,
    width: 470,
    height: 'fit-content',
    bgcolor: 'background.paper',
    boxShadow: 24,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'column',
    gap: '20px',
    p: 5,
};

function OtpModal({ open, handleClose, otp, setOtp, phone }) {
    const [seconds, setSeconds] = useState(60);
    const [isButtonDisabled, setIsButtonDisabled] = useState(false);

    const handleClick = () => {
        if (!isButtonDisabled) {
            setIsButtonDisabled(true);
            // resendEmail(email)
            //     .then((res) => {
            //         setIsButtonDisabled(true);
            //     })
            //     .catch((err) => {
            //         console.log(err);
            //     });
        }
    };

    useEffect(() => {
        if (seconds > 0 && isButtonDisabled) {
            const timer = setTimeout(() => {
                setSeconds((prevSeconds) => prevSeconds - 1);
            }, 1000);

            return () => clearTimeout(timer);
        } else {
            setSeconds(60);
            setIsButtonDisabled(false);
        }
    }, [seconds, isButtonDisabled]);

    return (
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Box sx={style}>
                <Typography>
                    Một mã OTP gồm 5 chữ số đã được gửi đến số điện thoại <b>{phone}</b>. Vui lòng kiểm tra tin nhắn và
                    nhập lại mã để kích hoạt tài khoản.
                </Typography>
                <OtpInput
                    value={otp}
                    onChange={setOtp}
                    numInputs={5}
                    inputStyle={{ width: '40px', height: '40px', border: '1px solid black' }}
                    containerStyle={{ gap: '5px', marginTop: '30px' }}
                    renderSeparator={<span>-</span>}
                    renderInput={(props) => <input {...props} />}
                />
                <Typography sx={{ mt: 1.5, mr: 10 }}>
                    Không nhận được mã OTP ?{' '}
                    <Box
                        component="span"
                        fontWeight="bold"
                        color={isButtonDisabled ? 'gray' : '#1976d2'}
                        sx={{ cursor: 'pointer' }}
                        onClick={handleClick}
                    >
                        Gửi lại{' '}
                        <Box component="span" visibility={isButtonDisabled ? 'visible' : 'hidden'}>
                            ({seconds})
                        </Box>
                    </Box>
                </Typography>
                <Button variant="contained" sx={{ ml: 30, mt: 2 }}>
                    Xác nhận
                </Button>
            </Box>
        </Modal>
    );
}

export default function Signup() {
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const [otp, setOtp] = useState('');
    const [phone, setPhone] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        // handleOpen();
        const data = new FormData(event.currentTarget);
        const phone = data.get('phone').trim();
        const password = data.get('password').trim();
        const repassword = data.get('repassword').trim();
        const shopId = data.get('shopId').trim();

        console.log({
            shopId,
            password,
            repassword,
            phone,
        });

        console.log(!shopId);

        if (!shopId) {
            toast.error('Không được để trống tên cửa hàng.', {
                autoClose: 2000,
            });
        } else if (!phone) {
            toast.error('Không được để trống số điện thoại.', {
                autoClose: 2000,
            });
        } else if (!password) {
            toast.error('Không được để trống mật khẩu.', {
                autoClose: 2000,
            });
        } else if (!repassword) {
            toast.error('Không được để trống xác nhận mật khẩu.', {
                autoClose: 2000,
            });
        } else if (password !== repassword) {
            toast.error('Mật khẩu không khớp.', {
                autoClose: 2000,
            });
        } else {
            const fetchApi = async () => {
                try {
                    const result = await SignUpServices.signup({phone, password});
                    console.log('signup', result);
                    if(result?.status == 404) {
                        toast.error(result?.data.message, {
                            autoClose: 2000,
                        });
                    } else if(result?.responseCode == 200) {
                        toast.success('Đăng ký thành công.', {
                            autoClose: 2000,
                            // onClose: () => navigate(-1),
                        });
                    }
                } catch (error) {
                    console.log('fetchApi SignUpServices Signup.js' + error);
                }
            };
            fetchApi();
        }
    };

    return (
        <>
            <ToastContainer />
            <ThemeProvider theme={defaultTheme}>
                <Container component="main" maxWidth="xs">
                    {/* <OtpModal handleClose={handleClose} open={open} otp={otp} setOtp={setOtp} phone={phone} /> */}
                    <CssBaseline />
                    <Box
                        sx={{
                            marginTop: 8,
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                        }}
                    >
                        <img className={cx('img')} src = "https://res.cloudinary.com/manhphuong/image/upload/v1701768561/nmp-logo_chokue.png" alt="logo" />
                        <Typography component="h1" variant="h5">
                            Đăng ký
                        </Typography>
                        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="shopId"
                                label="Tên cửa hàng"
                                name="shopId"
                                autoFocus
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="phone"
                                label="Số điện thoại"
                                name="phone"
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Mật khẩu"
                                type="password"
                                id="password"
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                name="repassword"
                                label="Nhập lại mật khẩu"
                                type="password"
                                id="repassword"
                            />
                            <Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
                                Đăng ký
                            </Button>
                            <Grid container>
                                <Grid item xs></Grid>
                                <Grid item>
                                    <Link href="/login" variant="body2">
                                        {'Đăng nhập'}
                                    </Link>
                                </Grid>
                            </Grid>
                        </Box>
                    </Box>
                </Container>
            </ThemeProvider>
        </>
    );
}
