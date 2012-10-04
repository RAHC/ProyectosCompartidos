CREATE procedure USUARIO_Add 
@alias varchar(25), 
@password varchar(100), 
@rol integer, 
@ubicacion varchar(10), 
@idContacto integer, 
@institucion integer, 
@nombres varchar(50), 
@apellido varchar(50), 
@telCont varchar(15), 
@celCont varchar(15), 
@mailInst varchar(60), 
@direccion varchar(100), 
@cargo varchar(50), 
@telInst varchar(15), 
@fax varchar(15), 
@mailPers varchar(60), 
@radio varchar(15),
@accion integer OUTPUT  
AS
BEGIN
	Begin Tran TUsuarioAdd
	Begin Try
	DECLARE @idc int;
	if @idContacto>0 begin
		set @idc = @idContacto;
		UPDATE CONTACTOS SET NOMBCONT=@nombres, APELLCONT=@apellido, TELCONT=@telCont, CELCONT=@celCont,
		MAILINSTCONT=@mailInst, DIRCONT=@direccion, CARGOCONT=@cargo, TELINSTCONT=@telInst, FAXCONT=@fax,
		MAILPERCONT=@mailPers, RADIOCONT=@radio WHERE IDCONT=@idc;
		
		INSERT INTO USUARIO(IDCONT, IDROL, IDUBIC, USERNAME, USERPASS, USERESTADO)
		values(@idc, @rol, @ubicacion, @alias, ENCRYPTBYPASSPHRASE('12345',@password), 'A' );
	end
	else begin
		set @idc = (select MAX(IDCONT) FROM CONTACTOS);
		if @idc>0 begin
			set @idc = @idc + 1;	
		end
		else begin
			set @idc = 1;
		end
		INSERT INTO CONTACTOS(IDCONT, IDINST, NOMBCONT, APELLCONT, TELCONT, CELCONT, MAILINSTCONT, DIRCONT, CARGOCONT, TELINSTCONT, FAXCONT, MAILPERCONT, RADIOCONT )
		values (@idc, @institucion, @nombres, @apellido, @telCont, @celCont, @mailInst, @direccion, @cargo, @telInst, @fax, @mailPers, @radio);
		
		INSERT INTO USUARIO(IDCONT, IDROL, IDUBIC, USERNAME, USERPASS, USERESTADO)
		values(@idc, @rol, @ubicacion, @alias, ENCRYPTBYPASSPHRASE('12345',@password), 'A' );
	end
	set @accion = @idc;
	COMMIT TRAN TUsuarioAdd
	End Try
	Begin Catch
		Set @accion = 0
		Rollback TRAN TUsuarioAdd
	End Catch
END

CREATE procedure VALIDACION_Add 
@idEv varchar(6), 
@corrInc varchar(7), 
@estado integer, 
@usuario integer, 
@descripcion varchar(200), 
@fecha datetime,
@idAccion integer OUTPUT
AS
BEGIN
	Begin Tran Tval
	Begin Try
	
		INSERT INTO ACCIONES(IDEV, CORRINC, IDESTADO, IDCONT, DESCACC, FECHORAREALACC, ESTADOACTACC, FECHORAALMACC, ESTADOACC)
		VALUES(@idEv, @corrInc, @estado, @usuario, @descripcion, @fecha, 2, CURRENT_TIMESTAMP, 'H' );
	
		UPDATE INCIDENTE SET IDESTADO=@estado WHERE IDEV=@idEv and CORRINC = @corrInc;
	
		set @idAccion = (SELECT MAX(IDACC)FROM ACCIONES WHERE IDEV=@idEv and CORRINC = @corrInc);
		COMMIT TRAN Tval
	End Try
	Begin Catch
		Set @idAccion = 0
		Rollback TRAN Tval
	End Catch

END


CREATE procedure CIERRE_Add @idEv varchar(6), @corrInc varchar(7), @estado integer, 
@usuario integer, @descripcion varchar(200), @fecha datetime
AS
BEGIN
	INSERT INTO ACCIONES(IDEV, CORRINC, IDESTADO, IDCONT, DESCACC, FECHORAREALACC, ESTADOACTACC, FECHORAALMACC, ESTADOACC)
	VALUES(@idEv, @corrInc, @estado, @usuario, @descripcion, @fecha, 7, CURRENT_TIMESTAMP, 'H' );
END;

CREATE procedure CONTACTO_Add 
@idInst integer, 
@nombre varchar(50), 
@apellido varchar(50), 
@tel varchar(15), 
@cel varchar(15), 
@mailInst varchar(60), 
@cargo varchar(25), 
@telInst varchar(15), 
@fax varchar(15), 
@mailPers varchar(60), 
@radio varchar(15),
@accion integer OUTPUT
AS
BEGIN
	Begin Tran TContactoAdd
	Begin Try
	DECLARE @idc int;
	set @idc = (select MAX(IDCONT) FROM CONTACTOS);
	if @idc>0 begin
			set @idc = @idc + 1;	
		end
		else begin
			set @idc = 1;
		end
	INSERT INTO CONTACTOS(IDCONT, IDINST, NOMBCONT, APELLCONT,
	 TELCONT, CELCONT, MAILINSTCONT, CARGOCONT, TELINSTCONT, FAXCONT, MAILPERCONT, 
	 RADIOCONT) VALUES(@idc, @idInst, @nombre, @apellido, @tel, @cel, @mailInst, @cargo, @telInst, @fax, @mailPers, @radio);
	
	set @accion = @idc;
	COMMIT TRAN TContactoAdd
	End Try
	Begin Catch
		Set @accion = 0
		Rollback TRAN TContactoAdd
	End Catch
END



CREATE procedure CONTACTOS_Update
@idInst integer, 
@nombre varchar(50), 
@apellido varchar(50), 
@tel varchar(15), 
@cel varchar(15), 
@mailInst varchar(60),
@dirCont varchar(100), 
@cargo varchar(25), 
@telInst varchar(15), 
@fax varchar(15), 
@mailPers varchar(60), 
@radio varchar(15),
@idCont integer,
@accion integer OUTPUT
AS
BEGIN
	Begin Tran TContactoAdd
	Begin Try
	
		UPDATE CONTACTOS SET IDINST=@idInst, NOMBCONT=@nombre, APELLCONT=@apellido, TELCONT=@tel,
		CELCONT=@cel, MAILINSTCONT=@mailInst, DIRCONT=@dirCont, CARGOCONT=@cargo, TELINSTCONT = @telInst,
		FAXCONT = @fax, MAILPERCONT=@mailPers, RADIOCONT=@radio WHERE IDCONT=@idCont;
		
		set @accion = @idCont;
	
		COMMIT TRAN TContactoAdd
	End Try
	Begin Catch
		Set @accion = 0
		Rollback TRAN TContactoAdd
	End Catch
END




